package com.example.androidtesting.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtesting.Entities.Spend
import com.example.androidtesting.Repository.SpendRepository
import kotlinx.coroutines.launch

class SpendViewModel(private val repository: SpendRepository)  : ViewModel(){


    fun insertSpendItem(item: Spend) = viewModelScope.launch {
        repository.insertSpend(item)
    }

    fun deleteSpendItem(item: Spend) = viewModelScope.launch {
        repository.deleteSpend(item)
    }

    fun getAllSpendItem() =  repository.getAllSpend()

}