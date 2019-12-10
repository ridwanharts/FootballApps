package com.jangkriek.ridwanharts.football.View

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeLeft
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.jangkriek.ridwanharts.football.R
import com.jangkriek.ridwanharts.football.R.id.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestFinal{
    @Rule
    @JvmField var activityRule = ActivityTestRule(MenuActivity::class.java)

    //Skenario MenuActivity untuk test behaviour tampilan
    @Test
    fun testViewBehaviour(){

        //tab previous match
        Thread.sleep(2000)
        onView(withId(rv_list_p)).check(matches(isDisplayed()))
        onView(withId(rv_list_p)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))

        Thread.sleep(2000)
        onView(withId(rv_list_p)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(11, click()))

        //klik favorit icon
        Thread.sleep(2000)
        onView(withId(fav)).perform(click())
        Thread.sleep(2000)

        //kembali ke fragment prev match
        Espresso.pressBack()


        //selesai
        Thread.sleep(2000)
    }

}