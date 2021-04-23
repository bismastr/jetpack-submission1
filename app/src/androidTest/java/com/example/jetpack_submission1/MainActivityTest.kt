package com.example.jetpack_submission1

import androidx.fragment.app.testing.FragmentScenario
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.jetpack_submission1.ui.movie.MovieFragment
import com.example.jetpack_submission1.utils.IdlingResources
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource(){
        IdlingRegistry.getInstance().register(IdlingResources.countingIdlingResources)
    }

    @After
    fun unregisterIdlingResource(){
        IdlingRegistry.getInstance().unregister(IdlingResources.countingIdlingResources)
    }

    @Test
    fun selectMovieTab() {
        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.navigation_tvshow)).perform(click())
    }

    @Test
    fun recyclerViewTest(){
        onView(withId(R.id.navigation_tvshow)).perform(click())
        onView(withId(R.id.rv_tv_discover)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_discover)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.rv_trending_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_trending_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.rv_movie_discover)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_discover)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
    }

    @Test
    fun loadDetail(){
        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.rv_movie_discover)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
    }





}