package com.example.androidtesting.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidtesting.Entities.Spend

@Dao
interface SpendDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertSpend(item:Spend)
    @Delete
    suspend fun deleteSpendItem(item:Spend)
    @Update
    suspend fun updateSpendItem(item:Spend)
    //Query Permet d'ecrire soit meme ses propre requete SQL
    @Query("SELECT * FROM spend_table")
    fun getAllSpendItems(): LiveData<List<Spend>>

    @Query("SELECT * FROM spend_table")
    fun getAllSpendItemsForTest(): List<Spend>




}