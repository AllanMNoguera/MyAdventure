package com.example.myadventure.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.example.android.trackmysleepquality.database.Game

@Dao
interface GameDatabaseDao {
    @Insert
    fun insert(night: Game)

    @Update
    fun update(night: Game)
}