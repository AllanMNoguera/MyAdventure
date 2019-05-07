package com.example.myadventure.game_space

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TutorialSpaceViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TutorialSpaceViewModel::class.java)) {
            return TutorialSpaceViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}