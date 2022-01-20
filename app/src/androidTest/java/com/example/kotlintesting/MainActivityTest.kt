package com.example.kotlintesting

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule @JvmField var activityTestRule = ActivityTestRule(MainActivity::class.java)
    lateinit var mainActivity: MainActivity

    @Before
    fun setup(){
        mainActivity = activityTestRule.activity
    }

    @Test
    fun checkInternet(){

    }

    @Test
    fun checkFirstItem(){
        Thread.sleep(3000)
        onView(withId(R.id.rvData)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        val nameItem = "Beavers"
        onView(withText(nameItem)).check(matches(isDisplayed()))
    }

    @Test
    fun checkLastItem() {
        Thread.sleep(3000)
        Thread.sleep(3000)
        onView(withId(R.id.rvData)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        val nameItem = "Language"
        onView(withText(nameItem)).check(matches(isDisplayed()))

    }


    @After
    fun tearDown(){
       mainActivity.finish()
    }

}