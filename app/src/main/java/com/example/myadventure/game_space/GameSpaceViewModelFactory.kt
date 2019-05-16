package com.example.myadventure.game_space

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myadventure.database.GameDatabaseDao

class GameSpaceViewModelFactory(
    private val dataSource: GameDatabaseDao,
    private val application: Application,
    private val Uri: Int) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameSpaceViewModel::class.java)) {
            return GameSpaceViewModel(dataSource, application, Uri) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}