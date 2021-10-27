package com.example.androidtesting.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "spend_table")
class Spend(

    @ColumnInfo(name = "item_amount")
    var amount: Int,
    @ColumnInfo(name = "item_description")
    var description: String
){
    @PrimaryKey(autoGenerate = true)
    var id:Long?=null

}