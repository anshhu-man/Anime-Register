package com.example.studentregister.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AnimeDao {
    @Insert
    suspend fun insertStudent(student: Anime)

    @Update
    suspend fun updateStudent(student: Anime)

    @Delete
    suspend fun deleteStudent(student: Anime)

    @Query("SELECT * FROM anime_data_table")
    fun getAllAnimes():LiveData<List<Anime>>
}