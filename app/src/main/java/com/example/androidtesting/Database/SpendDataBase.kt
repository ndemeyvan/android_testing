package com.example.androidtesting.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidtesting.Dao.SpendDao
import com.example.androidtesting.Entities.Spend

@Database(
    entities = [Spend::class],
    version = 2
)
abstract  class SpendDataBase: RoomDatabase() {

    abstract fun getSpendDao(): SpendDao

    companion object {
        @Volatile
        private var instance: SpendDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: createDatabase(
                        context
                    ).also { instance = it }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                SpendDataBase::class.java, "spend_db.db").build()
    }
}