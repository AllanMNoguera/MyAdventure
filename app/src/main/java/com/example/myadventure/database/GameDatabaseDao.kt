package com.example.myadventure.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GameDatabaseDao {
    @Insert
    fun insert(game: Game):Long

    @Update
    fun update(game: Game)

    @Query("SELECT * from game_performance_table WHERE gameId = :key")
    fun get(key: Long): LiveData<Game>

    @Insert
    fun insert(game: Pin):Long

    @Update
    fun update(game: Pin)

    @Query("SELECT * from report_pin_table WHERE pinId = :key")
    fun getPin(key: Long): LiveData<Pin>
}