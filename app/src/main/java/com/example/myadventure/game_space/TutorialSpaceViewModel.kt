package com.example.myadventure.game_space

import android.app.Application
import android.media.MediaPlayer
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class TutorialSpaceViewModel (
    application: Application,
    Uri: Int) : AndroidViewModel(application) {

    private val mediaPlayer: MediaPlayer = MediaPlayer.create(application, Uri);

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToGame = MutableLiveData<Int>()

    val navigateToGame: LiveData<Int>
    get() = _navigateToGame

    fun doneNavigating() {
        _navigateToGame.value = null
    }

    init {
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener { mp: MediaPlayer? ->
            _navigateToGame.value = 0
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
