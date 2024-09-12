package com.test.camera

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
object MessageBarTest {


    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun test(){

        composeTestRule.setContent {
            TestCompose(

            )
        }
    }

}