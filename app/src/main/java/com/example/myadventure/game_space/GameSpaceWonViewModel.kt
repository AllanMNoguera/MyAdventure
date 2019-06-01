package com.example.myadventure.game_space

import android.app.Application
import android.media.MediaPlayer
import androidx.lifecycle.*
import com.example.myadventure.database.Game
import com.example.myadventure.database.GameDatabaseDao
import kotlinx.coroutines.*

class GameSpaceWonViewModel(
    val gameKey: Long = 0L,
    val database: GameDatabaseDao,
    val application: Application,
    val Uri: Int) : ViewModel() {


    private val mediaPlayer: MediaPlayer = MediaPlayer.create(application, Uri)
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var gameScore = database.get(gameKey)

    val scoreString: LiveData<String> = Transformations.map(gameScore) { score ->
        "Score:\n" + score.gameScore
    }

    private val _navigateToGameMenu = MutableLiveData<Game>()

    val navigateToGameMenu: LiveData<Game>
        get() = _navigateToGameMenu

    fun doneNavigating() {
        _navigateToGameMenu.value = null
    }

    init {
        mediaPlayer.start()
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
