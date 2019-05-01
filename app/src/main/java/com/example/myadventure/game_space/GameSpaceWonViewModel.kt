package com.example.myadventure.game_space

import androidx.lifecycle.*
import com.example.myadventure.database.Game
import com.example.myadventure.database.GameDatabaseDao
import kotlinx.coroutines.*

class GameSpaceWonViewModel(
    private val gameKey: Long = 0L,
    val database: GameDatabaseDao) : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var gameScore = database.get(gameKey)

    val scoreString: LiveData<String> = Transformations.map(gameScore) { score ->
        "Score:" + score.gameName
    }

    private val _navigateToGameMenu = MutableLiveData<Game>()

    val navigateToGameMenu: LiveData<Game>
        get() = _navigateToGameMenu

    fun doneNavigating() {
        _navigateToGameMenu.value = null
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
