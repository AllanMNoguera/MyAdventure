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

    private var gameScore = MutableLiveData<Game?>()

    private val _navigateToGameMenu = MutableLiveData<Game>()

    val scoreString: LiveData<String> = Transformations.map(gameScore) { score ->
        "Score:" + score?.gameName
    }

    val navigateToGameMenu: LiveData<Game>
        get() = _navigateToGameMenu

    fun doneNavigating() {
        _navigateToGameMenu.value = null
    }

    init {
        initiateScoreFragment()
    }

    private fun initiateScoreFragment() {
        uiScope.launch {
            // IO is a thread pool for running operations that access the disk, such as
            // our Room database.
            withContext(Dispatchers.IO) {
                gameScore.value = database.get(gameKey).value ?: return@withContext
            }
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
