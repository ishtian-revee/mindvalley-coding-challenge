package com.mindvalley.mindvalleyapp.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import com.mindvalley.mindvalleyapp.R
import com.mindvalley.mindvalleyapp.di.AppModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class ChannelScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun screenTextTest() {
        composeRule.onAllNodesWithText(composeRule.activity.getString(R.string.channels))[0].assertIsDisplayed()
        composeRule.onAllNodesWithText(composeRule.activity.getString(R.string.new_episodes))[0].assertIsDisplayed()
        composeRule.onAllNodesWithText(composeRule.activity.getString(R.string.browse_by_categories))[0].assertIsDisplayed()
    }
}