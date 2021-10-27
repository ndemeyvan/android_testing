package com.example.androidtesting

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class ValidatorTest {


    ///Dans ce cas on veux tester
    // un cas ou les entres de l'utilisateur sont valide
    @Test
    fun whenInputIsValid(){
        val amount = 100
        val desc = "Some Random text"
        val result =  Validator.validateInput(amount,desc)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenInputInValid(){
        val amount = 0
        val desc = ""
        val result =  Validator.validateInput(amount,desc)
        assertThat(result).isEqualTo(false)
    }


}