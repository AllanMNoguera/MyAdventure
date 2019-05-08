package com.example.myadventure.game_space

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TutorialSpaceViewModelFactory(
    private val application: Application,
    private val uri: Int) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TutorialSpaceViewModel::class.java)) {
            return TutorialSpaceViewModel(application, uri) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}