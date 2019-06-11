package com.coffee.myadventure.game_detective

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TutorialDetectiveViewModelFactory(
    private val application: Application,
    private val uri: Int) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TutorialDetectiveViewModel::class.java)) {
            return TutorialDetectiveViewModel(application, uri) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}