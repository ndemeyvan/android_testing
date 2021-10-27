package com.example.androidtesting.ViewModel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidtesting.Dao.SpendDao
import com.example.androidtesting.Database.SpendDataBase
import com.example.androidtesting.Entities.Spend
import com.example.androidtesting.Repository.SpendRepository
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SpendViewModelTest : TestCase(){


    private lateinit var viewModel: SpendViewModel
    private lateinit var db:SpendDataBase
    private lateinit var  dao: SpendDao
    private lateinit var spendRepository: SpendRepository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        //Cree une Base de donnee dans la memoire volatile
        //allowMainThreadQueries sert a ne pas changer de Thread pendant le test
        db = Room.inMemoryDatabaseBuilder(context, SpendDataBase::class.java).allowMainThreadQueries().build()
        dao = db.getSpendDao()
        spendRepository = SpendRepository(db)
        viewModel = SpendViewModel(spendRepository)
    }

    @Test
    fun testSpendViewModel() {
        viewModel.insertSpendItem(Spend(5000,"Rendom text"))
        val result = viewModel.getAllSpendItem().getOrAwaitValue().find {
            it.amount == 5000 && it.description == "Rendom text"
        }
        assertThat(result!=null).isEqualTo(true)

    }


    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}