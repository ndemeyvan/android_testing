package com.example.androidtesting

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.runner.RunWith
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Test


@RunWith(AndroidJUnit4::class)
class SpendFragmentTest{

//    ///Declarer un scenario
//    private lateinit var scenario : FragmentScenario<SpendFragment>
//
////    @Before
//     fun setUp() {
//        scenario = launchFragmentInContainer(themeResId = R.style.Theme_AndroidTesting)
////        scenario.moveToState(Lifecycle.State.STARTED)
//    }

    @Test
    fun testAddingSpend() {
        val scenario = launchFragmentInContainer<SpendFragment>(
            initialState = Lifecycle.State.INITIALIZED
        )
        scenario.moveToState(Lifecycle.State.RESUMED)

        val amount = 100
        val desc = "Bought Eggs"
        //Espresso Matcher and Action
        onView(withId(R.id.etAmount)).perform(typeText(amount.toString()))
        onView(withId(R.id.etDescription)).perform(typeText(desc))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btSpend)).perform(click())
        //Assertion
        onView(withId(R.id.tvAddSpendStatus)).check(matches(withText("New spend add Successfully")))
    }


}
