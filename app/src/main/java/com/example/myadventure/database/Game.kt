package com.example.android.trackmysleepquality.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_performace_table")
data class Game(
    @PrimaryKey(autoGenerate = true)
    var gameId: Long = 0L,

    @ColumnInfo(name = "end_time_milli")
    var endTimeMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "consecutive_score")
    var gameScore: Int = -1,

    @ColumnInfo(name = "game_name")
    var gameName: String = ""
)