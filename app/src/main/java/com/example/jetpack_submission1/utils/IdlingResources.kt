package com.example.jetpack_submission1.utils

import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResources {
    private const val RESOURCE = "GLOBAL"

    @JvmField val countingIdlingResources = CountingIdlingResource(RESOURCE)

    fun increment(){
        countingIdlingResources.increment()
    }

    fun decrement(){
        if (!countingIdlingResources.isIdleNow){
            countingIdlingResources.decrement()
        }
    }
}