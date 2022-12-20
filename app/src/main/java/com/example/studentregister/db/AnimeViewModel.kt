package com.example.studentregister.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AnimeViewModel(private val dao: AnimeDao): ViewModel() {

    val animes = dao.getAllAnimes()

    fun insertStudent(anime: Anime)=viewModelScope.launch{
        dao.insertStudent(anime)
    }

    fun updateStudent(anime: Anime)=viewModelScope.launch{
        dao.updateStudent(anime)
    }

    fun deleteStudent(anime: Anime)=viewModelScope.launch{
        dao.deleteStudent(anime)
    }

}