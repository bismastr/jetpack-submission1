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
    //Check Recyclerview pada Tv dan Movie
    @Test
    fun recyclerViewTest(){
        onView(withId(R.id.navigation_tvshow)).perform(click())
        onView(withId(R.id.rv_tv_discover)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_trending_tvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_discover)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.rv_trending_tvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.rv_trending_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_trending_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.rv_movie_discover)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_discover)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
    }
    //Check Detail pada Movie
    @Test
    fun loadDetailMovie(){
        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.rv_movie_discover)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_overview_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.ratingbar_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailMovieTrending(){
        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.rv_trending_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_overview_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.ratingbar_movie)).check(matches(isDisplayed()))
    }
    //check detail tv
    @Test
    fun loadDetailTv(){
        onView(withId(R.id.navigation_tvshow)).perform(click())
        onView(withId(R.id.rv_tv_discover)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_overview_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.ratingbar_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_episode)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_seasons)).check(matches(isDisplayed()))
    }
    @Test
    fun loadDetailTvTrending(){
        onView(withId(R.id.navigation_tvshow)).perform(click())
        onView(withId(R.id.rv_trending_tvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_overview_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.ratingbar_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_episode)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_seasons)).check(matches(isDisplayed()))
    }





}