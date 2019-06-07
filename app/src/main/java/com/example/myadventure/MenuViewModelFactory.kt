package com.example.myadventure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myadventure.database.GameDatabaseDao

class MenuViewModelFactory(
    private val dataSource: GameDatabaseDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            return MenuViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
