package com.bahadori.metropolitanmuseum.core.database.dao

import androidx.test.filters.SmallTest
import com.bahadori.metropolitanmuseum.core.database.MetDatabase
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ObjectDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: MetDatabase

    private lateinit var objectDao: MetObjectDao

    @Before
    fun setup() {
        hiltRule.inject()
        objectDao = database.objectDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun testInsertObjects() = runTest {
        // Given
        val metObjects = listOf(
            FakeData.metObject.copy(objectID = 1),
            FakeData.metObject.copy(objectID = 2),
            FakeData.metObject.copy(objectID = 3),
        )

        // When
        val result = objectDao.insertObjects(metObjects)

        // Then
        assertThat(result.size).isEqualTo(metObjects.size)
    }

    @Test
    fun testInsertObject() = runTest {
        // Given
        val metObject = FakeData.metObject.copy(objectID = 1)

        // When
        val result = objectDao.insertObject(metObject)

        // Then
        assertThat(result).isGreaterThan(0)
    }

    @Test
    fun testGetObjects() = runTest {
        // Given
        val metObjects = listOf(
            FakeData.metObject.copy(objectID = 1),
            FakeData.metObject.copy(objectID = 2),
            FakeData.metObject.copy(objectID = 3),
        )
        objectDao.insertObjects(metObjects)

        // When
        val result = objectDao.getObjects().take(1).toList().firstOrNull()

        // Then
        assertThat(result).isNotNull()
        assertThat(result!!.size).isEqualTo(metObjects.size)
        assertThat(result[0]).isEqualTo(metObjects[0])
    }

    @Test
    fun testGetObject() = runTest {
        // Given
        val metObject = FakeData.metObject.copy(objectID = 1)
        objectDao.insertObject(metObject)

        // When
        val result = objectDao.getObject(metObject.objectID!!)

        // Then
        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(metObject)
    }

    @Test
    fun testDeleteObjects() = runTest {
        // Given
        val metObjects = listOf(
            FakeData.metObject.copy(objectID = 1),
            FakeData.metObject.copy(objectID = 2),
            FakeData.metObject.copy(objectID = 3),
        )
        objectDao.insertObjects(metObjects)

        // When
        objectDao.deleteObjects()
        val result = objectDao.getObjects().take(1).toList().firstOrNull()

        // Then
        assertThat(result).isEmpty()
    }
}