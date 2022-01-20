package com.example.kotlintesting

import android.view.View
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class MainActivityUTest {

    @Rule @JvmField var activityTestRule = ActivityTestRule(MainActivity::class.java)
    lateinit var mainActivity: MainActivity

    @Before
    fun setup(){
        mainActivity = activityTestRule.activity
    }

    @Test
    fun testLaunch(){
        val view = mainActivity.findViewById(R.id.rvData) as View
        assertNotNull(view)
    }

    @After
    fun tearDown(){
       mainActivity.finish()
    }

}