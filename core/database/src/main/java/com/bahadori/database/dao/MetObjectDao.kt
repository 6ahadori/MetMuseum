/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bahadori.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bahadori.database.model.MetObjectEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MetObjectDao {

    @Query(" SELECT * FROM metObjects WHERE objectID = :id")
    suspend fun getObject(id: Int): MetObjectEntity?

    @Query("SELECT * FROM metObjects")
    fun getObjects(): Flow<List<MetObjectEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertObjects(objects: List<MetObjectEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertObject(metObject: MetObjectEntity): Long

    @Query("DELETE FROM metObjects")
    suspend fun deleteObjects()
}
