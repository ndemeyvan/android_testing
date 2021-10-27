package com.example.androidtesting.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidtesting.Repository.SpendRepository

class SpendViewModelFactory(private val repository: SpendRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SpendViewModel(repository) as T
    }
}