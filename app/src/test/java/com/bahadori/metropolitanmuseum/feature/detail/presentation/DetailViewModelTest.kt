package com.bahadori.metropolitanmuseum.feature.detail.presentation

import androidx.lifecycle.SavedStateHandle
import com.bahadori.metropolitanmuseum.common.loading.LoadState
import com.bahadori.metropolitanmuseum.core.database.model.asMetObject
import com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response.asMetObjectEntity
import com.bahadori.metropolitanmuseum.feature.search.data.repository.FakeData
import com.bahadori.metropolitanmuseum.feature.search.domain.repository.MetObjectRepository
import com.bahadori.metropolitanmuseum.feature.search.presentation.MainDispatcherRule
import com.bahadori.metropolitanmuseum.model.data.MetObject
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`


@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = MainDispatcherRule()

    private val repository: MetObjectRepository = mockk()
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
            FakeData.metObjectDto.copy(objectID = objectID).asMetObjectEntity().asMetObject()
        val result = Result.success(metObject)
        coEvery { repository.getMetObject(objectID) } returns result

        // Act
        detailViewModel.event(DetailContract.Event.GetObject(objectID))

        // Assert
        val state = detailViewModel.state.value
        assertEquals(LoadState.NotLoading(), state.loading)
        assertEquals(metObject, state.currentObject)
    }

    @Test
    fun `test getObject error`() = runTest {
        // Arrange
        val objectID = 123
        val errorMsg = "Error fetching object"
        val result = Result.failure<MetObject>(Throwable(errorMsg))
        coEvery { repository.getMetObject(objectID) } returns result

        // Act
        detailViewModel.event(DetailContract.Event.GetObject(objectID))

        // Assert
        val state = detailViewModel.state.value
        assertEquals(LoadState.Error(errorMsg), state.loading)
        assertNull(state.currentObject)
    }
}
