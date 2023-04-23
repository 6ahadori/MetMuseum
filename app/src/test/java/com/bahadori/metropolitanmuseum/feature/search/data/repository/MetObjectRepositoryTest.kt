package com.bahadori.metropolitanmuseum.feature.search.data.repository

import com.bahadori.metropolitanmuseum.core.database.dao.MetObjectDao
import com.bahadori.metropolitanmuseum.core.database.model.asMetObject
import com.bahadori.metropolitanmuseum.core.network.retrofit.MetApi
import com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response.MetObjectDto
import com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response.SearchResponse
import com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response.asMetObjectEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.anyOrNull
import retrofit2.Response

@ExperimentalCoroutinesApi
class MetObjectRepositoryImplTest {

    @Mock
    private lateinit var api: MetApi

    @Mock
    private lateinit var dao: MetObjectDao

    private lateinit var repository: MetObjectRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = MetObjectRepositoryImpl(api, dao)
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
        `when`(api.search(query)).thenReturn(searchResponse)

        // When
        val result = repository.search(query)

        // Then
        assertThat(result).isEqualTo(objectIds)
        verify(api).search(query)
    }

    @Test
    fun testSearchWithValidQueryAndUnsuccessfulResponse() = runTest {
        // Given
        val query = "test query"
        val searchResponse =
            Response.error<SearchResponse>(404, Mockito.mock(okhttp3.ResponseBody::class.java))
        `when`(api.search(query)).thenReturn(searchResponse)

        // When
        val result = repository.search(query)

        // Then
        assertThat(result).isEqualTo(emptyList<Int>())
        verify(api).search(query)
    }

    @Test
    fun testGetMetObjects_whenDataExistsInDb_returnMetObjects() = runTest {
        // Given
        val objectIDs = intArrayOf(1, 2, 3)
        val object1 = FakeData.metObjectDto.copy(objectID = 1)
        val object2 = FakeData.metObjectDto.copy(objectID = 2)
        val object3 = FakeData.metObjectDto.copy(objectID = 3)

        `when`(dao.getObject(object1.objectID!!)).thenReturn(object1.asMetObjectEntity())
        `when`(dao.getObject(object2.objectID!!)).thenReturn(object2.asMetObjectEntity())
        `when`(dao.getObject(object3.objectID!!)).thenReturn(object3.asMetObjectEntity())

        // When
        val result = repository.getMetObjects(*objectIDs)

        // Then
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(
            listOf(
                object1.asMetObjectEntity().asMetObject(),
                object2.asMetObjectEntity().asMetObject(),
                object3.asMetObjectEntity().asMetObject()
            ),
        )
        verify(dao, times(3)).getObject(anyInt())
        verify(api, never()).getObject(anyInt())
    }

    @Test
    fun testGetMetObjects_whenSomeItemsExistInDb_returnMetObjectsAndFetchFromApi() = runTest {
        // Given
        val objectIDs = intArrayOf(1, 2, 3)
        val object1 = FakeData.metObjectDto.copy(objectID = 1)
        val object2 = FakeData.metObjectDto.copy(objectID = 2)

        `when`(dao.getObject(object1.objectID!!)).thenReturn(object1.asMetObjectEntity())
        `when`(dao.getObject(object2.objectID!!)).thenReturn(object2.asMetObjectEntity())
        `when`(dao.getObject(eq(3))).thenReturn(null)

        val response3 = FakeData.metObjectDto.copy(objectID = 1)
        `when`(api.getObject(3)).thenReturn(response3)

        // When
        val result = repository.getMetObjects(*objectIDs)

        // Then
        assertThat(result.isSuccess).isTrue()
        assertThat(
            result.getOrNull()
        ).isEqualTo(
            listOf(
                object1.asMetObjectEntity().asMetObject(),
                object2.asMetObjectEntity().asMetObject(),
                response3.asMetObjectEntity().asMetObject()
            )
        )
        verify(dao, times(3)).getObject(anyInt())
        verify(api).getObject(3)
        verify(dao).insertObject(response3.asMetObjectEntity())
    }

    @Test
    fun testGetMetObjects_whenNoneOfThemExistInDb_returnMetObjectsAndFetchFromApi() = runTest {
        // Given
        val objectIDs = intArrayOf(1, 2, 3)

        `when`(dao.getObject(anyInt())).thenReturn(null)

        val response1 = FakeData.metObjectDto.copy(objectID = 1)
        val response2 = FakeData.metObjectDto.copy(objectID = 2)
        val response3 = FakeData.metObjectDto.copy(objectID = 3)
        `when`(api.getObject(1)).thenReturn(response1)
        `when`(api.getObject(2)).thenReturn(response2)
        `when`(api.getObject(3)).thenReturn(response3)

        // When
        val result = repository.getMetObjects(*objectIDs)

        // Then
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(
            listOf(
                response1.asMetObjectEntity().asMetObject(),
                response2.asMetObjectEntity().asMetObject(),
                response3.asMetObjectEntity().asMetObject()
            ),
        )
        verify(dao, times(3)).getObject(anyInt())
        verify(api, times(3)).getObject(anyInt())
        verify(dao).insertObject(response1.asMetObjectEntity())
    }

    @Test
    fun testGetMetObjects_whenNoneOfThemExistInDbAndApiCallsFailed_returnEmptyList() = runTest {
        // Given
        val objectIDs = intArrayOf(1, 2, 3)

        `when`(dao.getObject(anyInt())).thenReturn(null)

        `when`(api.getObject(1)).thenReturn(null)
        `when`(api.getObject(2)).thenReturn(null)
        `when`(api.getObject(3)).thenReturn(null)

        // When
        val result = repository.getMetObjects(*objectIDs)

        // Then
        verify(dao, times(3)).getObject(anyInt())
        verify(api, times(3)).getObject(anyInt())
        verify(dao, never()).insertObject(anyOrNull())
    }

    @Test
    fun testGetMetObjectsWithEmptyObjectIds() = runTest {
        // Given
        val objectIds = emptyArray<Int>()
        val result = Result.success(listOfNotNull<MetObjectDto>())

        // When
        val deferredResult = repository.getMetObjects(*objectIds.toIntArray())

        // Then
        assertThat(deferredResult).isEqualTo(result)
        Mockito.verifyNoInteractions(dao)
        Mockito.verifyNoInteractions(api)
    }
}