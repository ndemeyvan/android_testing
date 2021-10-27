package com.example.androidtesting.Database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidtesting.Dao.SpendDao
import com.example.androidtesting.Entities.Spend
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat


@RunWith(AndroidJUnit4::class)
class SpendDataBaseTest : TestCase(){

    private lateinit var db:SpendDataBase
    private lateinit var  dao:SpendDao

    //@Before signifie que cette methode sera appele avant chanque test
    //Cette mthode doit tjours etre public
    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        //Cree une Base de donnee dans la memoire volatile
        db = Room.inMemoryDatabaseBuilder(context,SpendDataBase::class.java).build()
        dao = db.getSpendDao()
    }

    @Test
    fun getAllSpend() = runBlocking {
        val spend = Spend(5000,"Some Random Text")
        dao.insertSpend(spend)
        val spends = dao.getAllSpendItemsForTest()
        assertThat(spends.isNotEmpty()).isEqualTo(true)
    }

    @Test
    fun insertSpend() = runBlocking {
        val spend = Spend(5000,"Some Random Text")
        dao.insertSpend(spend)
        val spends = dao.getAllSpendItemsForTest()
        assertThat(spends.contains(spend)).isEqualTo(false)
    }

    @Test
    fun deleteSpend() = runBlocking{
        val spend = Spend(5000,"Some Random Text")
        //Insert Spend
        dao.insertSpend(spend)
        //Delete spend
        dao.deleteSpendItem(spend)
        val spends = dao.getAllSpendItemsForTest()
        assertThat(!spends.contains(spend)).isEqualTo(true)
    }

    //Sera appelle apres l'execution des test
    @After
    fun closeDb(){
        db.close()
    }

}