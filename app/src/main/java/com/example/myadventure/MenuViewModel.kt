package com.example.myadventure

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myadventure.database.Game
import com.example.myadventure.database.GameDatabaseDao
import kotlinx.coroutines.*

class MenuViewModel(
    val database: GameDatabaseDao) : ViewModel() {


    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToGameOne = MutableLiveData<Int>()

    val navigateToGameOne: LiveData<Int>
        get() = _navigateToGameOne

    private val _navigateToGameTwo = MutableLiveData<Int>()

    val navigateToGameTwo: LiveData<Int>
        get() = _navigateToGameTwo

    private val _navigateToGameTutorialOne = MutableLiveData<Int>()

    val navigateToGameTutorialOne: LiveData<Int>
        get() = _navigateToGameTutorialOne

    private val _navigateToGameTutorialTwo = MutableLiveData<Int>()

    val navigateToGameTutorialTwo: LiveData<Int>
        get() = _navigateToGameTutorialTwo

    fun onClickButtonGameOne(){
        uiScope.launch {
            if(getSpaceScores().isEmpty()){
                _navigateToGameTutorialOne.value = 1
            } else {
                _navigateToGameOne.value = 1
            }
        }
    }

    fun onClickButtonGameTwo(){
        uiScope.launch {
            if(getDetectiveScores().isEmpty()){
                _navigateToGameTutorialTwo.value = 1
            } else {
                _navigateToGameTwo.value = 1
            }
        }
    }

    private suspend fun getSpaceScores(): IntArray {
        return withContext(Dispatchers.IO) {
            var latestScores = database.getSpaceLatestScores()
            latestScores
        }
    }

    private suspend fun getDetectiveScores(): IntArray {
        return withContext(Dispatchers.IO) {
            var latestScores = database.getDetectiveLatestScores()
            latestScores
        }
    }

    fun doneNavigating() {
        _navigateToGameOne.value = null
        _navigateToGameTwo.value = null
        _navigateToGameTutorialOne.value = null
        _navigateToGameTutorialTwo.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
