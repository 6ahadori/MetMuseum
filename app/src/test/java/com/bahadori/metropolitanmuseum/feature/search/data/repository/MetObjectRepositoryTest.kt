package com.bahadori.metropolitanmuseum.feature.search.data.repository

import com.bahadori.metropolitanmuseum.core.network.retrofit.MetApi
import com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response.MetObject
import com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response.SearchResponse
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

@ExperimentalCoroutinesApi
class MetObjectRepositoryImplTest {

    @Mock
    private lateinit var api: MetApi

    private lateinit var repository: MetObjectRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = MetObjectRepositoryImpl(api)
    }

    @Test
    fun testSearchWithEmptyQuery() {
        // Given
        val query: String? = null

        // When
        val result = runBlocking {
            repository.search(query)
        }

        // Then
        assertThat(result).isEqualTo(emptyList<Int>())
        Mockito.verifyNoInteractions(api)
    }

    @Test
    fun testSearchWithValidQueryAndSuccessfulResponse() = runTest {
        // Given
        val query = "test query"
        val objectIds = listOf(1, 2, 3)
        val searchResponse = Response.success(SearchResponse(3, objectIds))
        Mockito.`when`(api.search(query)).thenReturn(searchResponse)

        // When
        val result = repository.search(query)

        // Then
        assertThat(result).isEqualTo(objectIds)
        Mockito.verify(api).search(query)
    }

    @Test
    fun testSearchWithValidQueryAndUnsuccessfulResponse() = runTest {
        // Given
        val query = "test query"
        val searchResponse =
            Response.error<SearchResponse>(404, Mockito.mock(okhttp3.ResponseBody::class.java))
        Mockito.`when`(api.search(query)).thenReturn(searchResponse)

        // When
        val result = repository.search(query)

        // Then
        assertThat(result).isEqualTo(emptyList<Int>())
        Mockito.verify(api).search(query)
    }

    @Test
    fun testGetMetObjectsWithValidObjectIdsAndSuccessfulResponse() = runTest {
        // Given
        val objectIds = listOf(1, 2, 3)
        val metObjects = listOf(
            FakeData.metObject.copy(objectID = 1),
            FakeData.metObject.copy(objectID = 2),
            FakeData.metObject.copy(objectID = 3),
        )
        val deferredMetObjects = metObjects.map { CompletableDeferred(it) }
        val result = Result.success(metObjects)
        val d = deferredMetObjects[0]
        Mockito.`when`(api.getObject(1)).thenReturn(deferredMetObjects[0].await())
        Mockito.`when`(api.getObject(2)).thenReturn(deferredMetObjects[1].await())
        Mockito.`when`(api.getObject(3)).thenReturn(deferredMetObjects[2].await())

        // When
        val deferredResult = repository.getMetObjects(*objectIds.toIntArray())


        // Then
        assertThat(result).isEqualTo(deferredResult)
        Mockito.verify(api).getObject(1)
        Mockito.verify(api).getObject(2)
        Mockito.verify(api).getObject(3)
    }

    @Test
    fun testGetMetObjectsWithValidObjectIdsAndUnsuccessfulResponse() = runTest {
        // Given
        val objectIds = arrayOf(1, 2, 3)
        val result = Result.success(listOfNotNull<MetObject>())
        Mockito.`when`(api.getObject(1)).thenReturn(null)
        Mockito.`when`(api.getObject(2)).thenReturn(null)
        Mockito.`when`(api.getObject(3)).thenReturn(null)

        // When
        val deferredResult = repository.getMetObjects(*objectIds.toIntArray())


        // Then
        assertThat(deferredResult).isEqualTo(result)
        Mockito.verify(api).getObject(1)
        Mockito.verify(api).getObject(2)
        Mockito.verify(api).getObject(3)
    }

    @Test
    fun testGetMetObjectsWithValidAndInvalidObjectIds() = runTest {
        // Given
        val objectIds = arrayOf(1, 2, 3)
        val deferredMetObjects = listOf(
            CompletableDeferred(null),
            CompletableDeferred(FakeData.metObject.copy(objectID = 2)),
            CompletableDeferred<MetObject?>(null)
        )
        val result = Result.success(listOf(FakeData.metObject.copy(objectID = 2)))
        Mockito.`when`(api.getObject(1)).thenReturn(null)
        Mockito.`when`(api.getObject(2)).thenReturn(deferredMetObjects[1].await())
        Mockito.`when`(api.getObject(3)).thenReturn(null)

        // When
        val deferredResult = repository.getMetObjects(*objectIds.toIntArray())


        // Then
        assertThat(deferredResult).isEqualTo(result)
        Mockito.verify(api).getObject(1)
        Mockito.verify(api).getObject(2)
        Mockito.verify(api).getObject(3)
    }

    @Test
    fun testGetMetObjectsWithEmptyObjectIds() = runTest {
        // Given
        val objectIds = emptyArray<Int>()
        val result = Result.success(listOfNotNull<MetObject>())

        // When
        val deferredResult = repository.getMetObjects(*objectIds.toIntArray())

        // Then
        assertThat(deferredResult).isEqualTo(result)
        Mockito.verifyNoInteractions(api)
    }
}