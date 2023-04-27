package com.bahadori.detail

import androidx.lifecycle.SavedStateHandle
import com.bahadori.testing.data.FakeData
import com.bahadori.testing.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = MainDispatcherRule()

    private val repository: com.bahadori.domain.MetObjectRepository = mockk()
    private lateinit var stateHandle: SavedStateHandle


    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        // Create a SavedStateHandle with the desired values
        val stateHandle = SavedStateHandle()
        stateHandle["objectID"] = 123

        // Initialize ViewModel with mocked dependencies
        detailViewModel = DetailViewModel(repository, stateHandle)
    }


    @Test
    fun `test getObject success`() = runTest {
        // Arrange
        val objectID = 123
        val metObject =
            FakeData.metObject.copy(objectID = objectID)
        val result = Result.success(metObject)
        coEvery { repository.getMetObject(objectID) } returns result

        // Act
        detailViewModel.event(DetailContract.Event.GetObject(objectID))

        // Assert
        val state = detailViewModel.state.value
        assertEquals(com.bahadori.common.loading.LoadState.NotLoading(), state.loading)
        assertEquals(metObject, state.currentObject)
    }

    @Test
    fun `test getObject error`() = runTest {
        // Arrange
        val objectID = 123
        val errorMsg = "Error fetching object"
        val result = Result.failure<com.bahadori.model.MetObject>(Throwable(errorMsg))
        coEvery { repository.getMetObject(objectID) } returns result

        // Act
        detailViewModel.event(DetailContract.Event.GetObject(objectID))

        // Assert
        val state = detailViewModel.state.value
        assertEquals(com.bahadori.common.loading.LoadState.Error(errorMsg), state.loading)
        assertNull(state.currentObject)
    }
}
