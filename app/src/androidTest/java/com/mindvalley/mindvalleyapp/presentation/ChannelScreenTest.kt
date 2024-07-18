package com.mindvalley.mindvalleyapp.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import com.mindvalley.mindvalleyapp.R
import com.mindvalley.mindvalleyapp.di.AppModule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class ChannelScreenTest {

//    @get:Rule
//    val composeTestRule = createAndroidComposeRule<MainActivity>()
//
//    @Test
//    fun channelContentTest() {
//        composeTestRule.onAllNodesWithText(composeTestRule.activity.getString(R.string.channels))[0].assertIsDisplayed()
//        composeTestRule.onAllNodesWithText(composeTestRule.activity.getString(R.string.new_episodes))[0].assertIsDisplayed()
//        composeTestRule.onAllNodesWithText(composeTestRule.activity.getString(R.string.browse_by_categories))[0].assertIsDisplayed()
//        composeTestRule.onAllNodesWithTag("episode_list")[0].assertIsDisplayed()
//        composeTestRule.onAllNodesWithTag("channel_list")[0].assertIsDisplayed()
//        composeTestRule.onAllNodesWithTag("category_list")[0].assertIsDisplayed()
//    }

}