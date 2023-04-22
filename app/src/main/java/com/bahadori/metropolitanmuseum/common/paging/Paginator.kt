package com.bahadori.metropolitanmuseum.common.paging

interface Paginator<Item> {
    suspend fun loadAllObjectIDs()
    suspend fun loadNextItems()
    fun reset()
}