package com.bahadori.search

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.bahadori.common.loading.LoadState
import com.bahadori.testing.data.FakeData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class SearchScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoadingViewWhenLoadStateIsLoadingAndObjectsListIsEmpty() {
        // Set the state to loading with an empty objects list
        val initialState = SearchContract.State(loading = LoadState.Loading())
        val state = MutableStateFlow(initialState).asStateFlow()

        // Launch the Compose screen with the mocked state flow
        composeTestRule.setContent {
            SearchScreen(
                state = state.value,
                onQueryChanged = {},
                onLoadObjects = {},
                onObjectClicked = {}
            )
        }

        // Assert that the loading view is displayed
        composeTestRule.onNodeWithTag("loading")
            .assertIsDisplayed()
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    @Test
    fun testNoLoadingViewWhenObjectsAreNotEmptyOrLoadStateIsNotLoading() {
        // Set the state with non-empty objects and a non-loading load state
        val initialState = SearchContract.State(
            objects = listOf(
                FakeData.metObject.copy(objectID = 1),
                FakeData.metObject.copy(objectID = 2),
                FakeData.metObject.copy(objectID = 3),
            )
        )
        val stateFlow = MutableStateFlow(initialState)

        // Launch the Compose screen with the mocked state flow
        composeTestRule.setContent {
            SearchScreen(
                state = stateFlow.value,
                onQueryChanged = {},
                onLoadObjects = {},
                onObjectClicked = {}
            )
        }

        // Assert that the loading view is not displayed
        composeTestRule.onNodeWithTag("loading")
            .assertDoesNotExist()
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    @Test
    fun testShowColumnWithNoItemToShowTextWhenTextFieldIsEmpty() {
        // Set the state with an empty query and no objects
        val initialState = SearchContract.State(query = "", objects = listOf())
        val stateFlow = MutableStateFlow(initialState)

        // Launch the Compose screen with the mocked state flow
        composeTestRule.setContent {
            SearchScreen(
                state = stateFlow.value,
                onQueryChanged = {},
                onLoadObjects = {},
                onObjectClicked = {}
            )
        }

        // Assert that the column with the "No item to show" text is displayed
        composeTestRule.onNodeWithTag("empty_list")
            .assertIsDisplayed()
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    @Test
    fun testShowLazyColumnWhenQueryEnteredAndDataRetrievedSuccessfully() = runTest {
        // Set the state with a non-empty query and some objects
        val initialState = SearchContract.State(
            query = "example query", objects = listOf(
                FakeData.metObject.copy(objectID = 1),
                FakeData.metObject.copy(objectID = 2),
                FakeData.metObject.copy(objectID = 3),
            )
        )
        val stateFlow = MutableStateFlow(initialState)

        // Launch the Compose screen with the mocked state flow
        composeTestRule.setContent {
            SearchScreen(
                state = stateFlow.value,
                onQueryChanged = {},
                onLoadObjects = {},
                onObjectClicked = {}
            )
        }

        // Assert that the LazyColumn is displayed
        composeTestRule.onNodeWithTag("search_list")
            .assertIsDisplayed()
    }
}
