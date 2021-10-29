package com.example.androidtesting

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

//Ici on va creer un test qui va tester un fragment vu
// que notre application tourne dans une seul
// activite donc on va tester toute l'application

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {


    private lateinit var scenario:ActivityScenario<MainActivity>

    @Before
    fun setup(){
        scenario = launchActivity()
        scenario.moveToState(Lifecycle.State.RESUMED)
    }


    @Test
    fun testAddingSpend() {
        onView(withId(R.id.fvAddSpend)).perform(click())
        val amount = (0..10000).random()
        val desc =  getRandomString(50)
        //Espresso Matcher and Action
        onView(withId(R.id.etAmount)).perform(ViewActions.typeText(amount.toString()))
        onView(withId(R.id.etDescription)).perform(ViewActions.typeText(desc))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btSpend)).perform(click())
        //Assertion
        onView(withText(amount.toString())).check(matches(isDisplayed()))
        onView(withText(desc)).check(matches(isDisplayed()))
    }

    fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }





}