package com.bahadori.common.paging

interface Paginator<Item> {
    suspend fun loadAllObjectIDs()
    suspend fun loadNextItems()
    fun reset()
}