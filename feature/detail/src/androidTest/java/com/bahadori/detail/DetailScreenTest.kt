package com.bahadori.detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.bahadori.testing.data.FakeData
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testBackButtonClickedWhenGalleryIsClosed() {
        var backButtonClicked = false

        // Launch the Compose screen with the BackButton click listener
        composeTestRule.setContent {
            DetailScreen(
                state = DetailContract.State(showGallery = false),
                onGalleryClicked = {},
                onBackClicked = { backButtonClicked = true }
            )
        }

        // Perform a click on the BackButton
        composeTestRule.onNodeWithTag("back_button")
            .performClick()

        // Assert that the backButtonClicked flag is true
        assertThat(backButtonClicked).isTrue()
    }

    @Test
    fun testGalleryButtonClicked() {
        val obj = FakeData.metObject.copy(objectID = 1, primaryImage = "foo.com")
        val initialState = DetailContract.State(currentObject = obj, images = listOf(obj.primaryImage!!))
        val state = MutableStateFlow(initialState)

        var galleryButtonClicked = false

        // Launch the Compose screen with the GalleryButton click listener
        composeTestRule.setContent {
            DetailScreen(
                state = state.value,
                onGalleryClicked = { galleryButtonClicked = true },
                onBackClicked = {}
            )
        }

        // Perform a click on the GalleryButton
        composeTestRule.onNodeWithTag("camera_button")
            .performClick()

        // Assert that the galleryButtonClicked flag is true
        assertThat(galleryButtonClicked).isTrue()
    }

    @Test
    fun testDetailViewDisplayed() {
        // Set the state with a valid MetObject
        val initialState = DetailContract.State(
            currentObject = FakeData.metObject.copy(objectID = 1)
        )
        val stateFlow = MutableStateFlow(initialState)

        // Launch the Compose screen with the mocked state flow
        composeTestRule.setContent {
            DetailScreen(
                state = stateFlow.value,
                onGalleryClicked = {},
                onBackClicked = {}
            )
        }

        // Assert that the DetailView is displayed
        composeTestRule.onNodeWithTag("detail_view")
            .assertIsDisplayed()
    }

    @Test
    fun testNoObjectDisplayed() {
        // Set the state with a null currentObject
        val initialState = DetailContract.State(currentObject = null)
        val stateFlow = MutableStateFlow(initialState)

        // Launch the Compose screen with the mocked state flow
        composeTestRule.setContent {
            DetailScreen(
                state = stateFlow.value,
                onGalleryClicked = {},
                onBackClicked = {}
            )
        }

        // Assert that the NoObject view is displayed
        composeTestRule.onNodeWithTag("no_object")
            .assertIsDisplayed()
    }
}
