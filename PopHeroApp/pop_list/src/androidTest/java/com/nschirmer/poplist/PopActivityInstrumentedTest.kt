package com.nschirmer.poplist

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
@LargeTest
class PopActivityInstrumentedTest {

    @get:Rule @JvmField var activityActivityTestRule = ActivityTestRule(PopListActivity::class.java)

    @Test
    fun nextYearTest() {
        onView(withId(com.nschirmer.widgets.R.id.forward_button)).perform(click())
    }

}
