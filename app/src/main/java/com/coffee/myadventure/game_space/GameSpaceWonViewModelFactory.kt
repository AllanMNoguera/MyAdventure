package com.coffee.myadventure.game_space

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coffee.myadventure.database.GameDatabaseDao

class GameSpaceWonViewModelFactory(
    private val dataSource: GameDatabaseDao,
    private val gameId: Long,
    private val application: Application,
    private val Uri: Int) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameSpaceWonViewModel::class.java)) {
            return GameSpaceWonViewModel(gameId, dataSource, application, Uri) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}