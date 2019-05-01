package com.example.myadventure.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    @Query("SELECT * FROM game_performance_table ORDER BY gameId DESC LIMIT 1")
    fun getThisGame(): Game
}