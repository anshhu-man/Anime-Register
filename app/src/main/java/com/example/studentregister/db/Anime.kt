package com.example.studentregister.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_data_table")
data class Anime(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "anime_id")
    var id:Int,
    @ColumnInfo(name = "anime_name")
    var name:String,
    @ColumnInfo(name = "anime_email")
    var dialogue:String
)