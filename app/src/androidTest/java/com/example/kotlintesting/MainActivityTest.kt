package com.example.kotlintesting

//import androidx.support.test.espresso.assertion.ViewAssertions.matches
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.kotlintesting.facts.model.FactsModel
import com.google.gson.Gson
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityETest {

    @Rule @JvmField var activityTestRule = ActivityTestRule(MainActivity::class.java)
    lateinit var mainActivity: MainActivity

    lateinit var modelData: FactsModel

    @Before
    fun setup(){
        mainActivity = activityTestRule.activity
        val jsonData = "{\"title\":\"About Canada\",\"rows\":[{\"title\":\"Beavers\",\"description\":\"Beavers build dams and lodges using tree branches, vegetation, rocks and mud; they chew down trees for building material. Dams impound water and lodges serve as shelters.\",\"imageHref\":\"http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg\"},{\"title\":\"Flag\",\"description\":\"null\",\"imageHref\":\"http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg\"},{\"title\":\"Transportation\",\"description\":\"The Department of Transport was created in 1935 by the government of William Lyon Mackenzie King in recognition of the changing transportation environment in Canada at the time.\",\"imageHref\":\"http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg\"},{\"title\":\"Hockey Night in Canada\",\"description\":\"Until the 1990s, there was only one game televised each Saturday night in any particular locality and up to 1968, regular season games were still not broadcast in their entirety.\",\"imageHref\":\"http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg\"}]}"
        modelData = Gson().fromJson(jsonData, FactsModel::class.java)
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
        Thread.sleep(3100)
//        onView(withId(R.id.rvData))
//            .check(matches(withItemCount(14)))

        Espresso.onView(withId(R.id.rvData)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(13, click()))
        val nameItem: String = "Language"
        onView(withText(nameItem)).check(matches(isDisplayed()))

    }


    @After
    fun tearDown(){
       mainActivity.finish()
    }

}