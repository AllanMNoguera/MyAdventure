package com.example.myadventure.database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "report_pin_table")
data class Pin(
    @PrimaryKey(autoGenerate = true)
    var pinId: Long = 0L,

    @ColumnInfo(name = "pin")
    var pin: Int = -1
)