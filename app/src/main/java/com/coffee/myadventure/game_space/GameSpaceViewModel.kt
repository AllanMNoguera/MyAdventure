package com.coffee.myadventure.game_space

import android.app.Application
import android.media.MediaPlayer
import androidx.lifecycle.*
import com.coffee.myadventure.database.GameDatabaseDao
import com.coffee.myadventure.database.Game
import com.coffee.myadventure.getSpaceAnswers
import com.coffee.myadventure.getSpaceQuestion
import com.coffee.myadventure.getSpaceScore
import kotlinx.coroutines.*

class GameSpaceViewModel(
        val database: GameDatabaseDao,
        application: Application,
        Uri: Int) : AndroidViewModel(application) {

        private val mediaPlayer: MediaPlayer = MediaPlayer.create(application, Uri)

        private var viewModelJob = Job()

        private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

        private var thisgame = MutableLiveData<Game?>()

        private var question = MutableLiveData<String?>()

        private var answers = MutableLiveData<List<String>>()

        private var tries = 10

        val questionString: LiveData<String> = Transformations.map(question) { question ->
                question
        }

        val answerString: LiveData<List<String>> = Transformations.map(answers) { answers ->
            answers
        }

        private val _navigateToGameOneScore = MutableLiveData<Game>()

        val navigateToGameScore: LiveData<Game>
        get() = _navigateToGameOneScore

        fun doneNavigating() {
            _navigateToGameOneScore.value = null
        }

        init {
            initializeGame()
            setQuestionAnswers()
        }

        private fun setQuestionAnswers() {
            question.value = getSpaceQuestion()
            answers.value = getSpaceAnswers()
        }

        private fun initializeGame() {
            uiScope.launch {
                thisgame.value = Game(gameName = "space_game", gameScore = 0)
            }
        }

        private suspend fun insert(game: Game): Long {
            return withContext(Dispatchers.IO) {
                var scoreId = database.insert(game)
                scoreId
            }
        }

        fun answerQuestion(answer: Int) {
            if(tries > 0) {
                val score = getSpaceScore(question.value, answers.value?.get(answer))
                thisgame.value?.gameScore = thisgame.value?.gameScore!!.plus(score)
                setQuestionAnswers()
            } else {
                onEndGame()
                tries = 10
            }
            tries--
        }

        fun onPause() {
            mediaPlayer.pause()
        }

        fun onResume() {
            mediaPlayer.start()
        }

        fun onEndGame() {
            uiScope.launch {
                // In Kotlin, the return@label syntax is used for specifying which function among
                // several nested ones this statement returns from.
                // In this case, we are specifying to return from launch(),
                // not the lambda.
                val endGame = thisgame.value ?: return@launch

                endGame.endTimeMilli = System.currentTimeMillis()

                endGame.gameId = insert(endGame)

                _navigateToGameOneScore.value = endGame
            }
        }

        /**
         * Called when the ViewModel is dismantled.
         * At this point, we want to cancel all coroutines;
         * otherwise we end up with processes that have nowhere to return to
         * using memory and resources.
         */
        override fun onCleared() {
            super.onCleared()
            mediaPlayer.stop()
            viewModelJob.cancel()
        }
}
