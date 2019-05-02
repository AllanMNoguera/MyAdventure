package com.example.myadventure.game_space

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myadventure.database.GameDatabaseDao

class GameDetectiveWonViewModelFactory(
    private val dataSource: GameDatabaseDao,
    private val gameId: Long) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameDetectiveWonViewModel::class.java)) {
            return GameDetectiveWonViewModel(gameId, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}