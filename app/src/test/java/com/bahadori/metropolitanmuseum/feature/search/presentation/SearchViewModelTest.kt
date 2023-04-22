package com.bahadori.metropolitanmuseum.feature.search.presentation

import com.bahadori.metropolitanmuseum.common.loading.LoadState
import com.bahadori.metropolitanmuseum.feature.search.domain.repository.MetObjectRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = MainDispatcherRule()

    private val metObjectRepository: MetObjectRepository = mockk()
    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        searchViewModel = SearchViewModel(metObjectRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test OnQueryEntered event updates state and loads all object IDs`() = runTest {
        // Given
        val query = "example query"
        val event = SearchContract.Event.OnQueryEntered(query)

        // When
        searchViewModel.event(event)

        // Then
        coVerify { metObjectRepository.search(query) }
        assertEquals(
            searchViewModel.state.value.loading,
            LoadState.Loading()
        )
        assertEquals(
            searchViewModel.state.value.page,
            1
        )
    }

    @Test
    fun `test OnLoadMore event loads next items`() = runTest {
        // Given
        val event = SearchContract.Event.OnLoadMore
        coEvery { metObjectRepository.getMetObjects(*anyIntVararg()) } returns Result.success(emptyList())
        // When
        searchViewModel.event(event)

        // Then
        coVerify { metObjectRepository.getMetObjects(*anyIntVararg()) }
    }


    @Test
    fun `test OnQueryEntered event cancels previous search job and starts a new one`() = runTest {
        // Given
        val query1 = "query1"
        val query2 = "query2"
        val event1 = SearchContract.Event.OnQueryEntered(query1)
        val event2 = SearchContract.Event.OnQueryEntered(query2)

        // When
        searchViewModel.event(event1)
        searchViewModel.event(event2)

        // Then
        coVerifySequence {
            metObjectRepository.search(query1)
            metObjectRepository.search(query2)
        }
    }

}
