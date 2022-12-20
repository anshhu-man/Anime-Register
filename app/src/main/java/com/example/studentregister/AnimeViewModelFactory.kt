package com.example.studentregister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studentregister.db.AnimeDao
import com.example.studentregister.db.AnimeViewModel

class AnimeViewModelFactory(private val dao: AnimeDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AnimeViewModel::class.java)) {
            return AnimeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}