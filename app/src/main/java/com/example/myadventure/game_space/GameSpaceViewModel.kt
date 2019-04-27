package com.example.myadventure.game_space

import android.app.Application
import androidx.lifecycle.*
import com.example.android.trackmysleepquality.database.Game
import kotlinx.coroutines.*

class GameSpaceViewModel(
        application: Application) : AndroidViewModel(application) {

        private var viewModelJob = Job()

        private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

        private var thisgame = MutableLiveData<Game?>()

        private val _navigateToGameOneScore = MutableLiveData<Game>()

        val navigateToGameScore: LiveData<Game>
        get() = _navigateToGameOneScore

        fun doneNavigating() {
            _navigateToGameOneScore.value = null
        }

        init {
            initializeGame()
        }

        private fun initializeGame() {
            uiScope.launch {
                thisgame.value = Game(gameName = "space_game")
            }
        }

        fun onEndGame() {
            uiScope.launch {
                // In Kotlin, the return@label syntax is used for specifying which function among
                // several nested ones this statement returns from.
                // In this case, we are specifying to return from launch(),
                // not the lambda.
                val endGame = thisgame.value ?: return@launch

                // Update the night in the database to add the end time.
                endGame.endTimeMilli = System.currentTimeMillis()

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
            viewModelJob.cancel()
        }
}
