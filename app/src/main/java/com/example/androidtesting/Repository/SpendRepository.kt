package com.example.androidtesting.Repository

import androidx.lifecycle.LiveData
import com.example.androidtesting.Database.SpendDataBase
import com.example.androidtesting.Entities.Spend

class SpendRepository (private val db: SpendDataBase) {

    suspend fun insertSpend(item:Spend){
        return db.getSpendDao().insertSpend(item)
    }

    suspend fun deleteSpend(item:Spend){
        return db.getSpendDao().deleteSpendItem(item)
    }

    fun getAllSpend(): LiveData<List<Spend>> {
        return db.getSpendDao().getAllSpendItems()
    }
}