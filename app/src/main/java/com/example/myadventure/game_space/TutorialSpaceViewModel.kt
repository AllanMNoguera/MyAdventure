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

    /**
     * Metodo que indica incrementalmente cual es la siguiente
     * imagen del tutorial a mostrar.
     */
    fun onNext() {
        if(_navigateToNext.value == null){
            _navigateToNext.value = 0
        } else {
            _navigateToNext.value = _navigateToNext.value!! + 1
        }
        if(_navigateToNext.value!! >= 2){
            onFinishTutorial()
        }
    }

    /**
     * Este metodo provoca que se inicie el siguiente
     * Fragmento.
     */
    fun onFinishTutorial() {
        _navigateToGame.value = 0
        mediaPlayer.stop()
    }

    /**
     * Este metodo se llama cuando se destrulle el ViewModel.
     * Aqui se cancelan todas las corutinas.
     */
    override fun onCleared() {
        mediaPlayer.stop()
        super.onCleared()
        viewModelJob.cancel()
    }
}
