package com.example.myadventure.game_detective

import android.app.Application
import android.media.MediaPlayer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class TutorialDetectiveViewModel (
    application: Application,
    Uri: Int) : AndroidViewModel(application) {

    private val mediaPlayer: MediaPlayer = MediaPlayer.create(application, Uri);

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToGame = MutableLiveData<Int>()

    val navigateToGame: LiveData<Int>
        get() = _navigateToGame

    private val _navigateToNext = MutableLiveData<Int>()

    val navigateToNext: LiveData<Int>
        get() = _navigateToNext

    fun doneNavigating() {
        _navigateToGame.value = null
    }

    init {
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener { mp: MediaPlayer? ->
            _navigateToGame.value = 0
        }
    }

    fun onNext() {
        if(_navigateToNext.value == null){
            _navigateToNext.value = 0
        } else {
            _navigateToNext.value = _navigateToNext.value!! + 1
        }
        if(_navigateToNext.value!! >= 4){
            onFinishTutorial()
        }
    }

    fun onFinishTutorial() {
        _navigateToGame.value = 0
        mediaPlayer.stop()
    }

    /**
     * Called when the ViewModel is dismantled.
     * At this point, we want to cancel all coroutines;
     * otherwise we end up with processes that have nowhere to return to
     * using memory and resources.
     */
    override fun onCleared() {
        mediaPlayer.stop()
        super.onCleared()
        viewModelJob.cancel()
    }
}
