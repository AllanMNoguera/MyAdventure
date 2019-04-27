package com.example.myadventure.game_space

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GameSpaceViewModelFactory(
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameSpaceViewModel::class.java)) {
            return GameSpaceViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}