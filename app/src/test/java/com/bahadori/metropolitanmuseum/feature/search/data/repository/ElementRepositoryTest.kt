package com.bahadori.metropolitanmuseum.feature.search.data.repository

import com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response.Element
import com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response.SearchResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson

@OptIn(ExperimentalCoroutinesApi::class)
class ElementRepositoryTest {

    lateinit var repository: FakeElementRepository

    @Before
    fun setup() {
        repository = FakeElementRepository()
    }

    @Test
    fun returnResultOnSearch() = runTest {

    }

}