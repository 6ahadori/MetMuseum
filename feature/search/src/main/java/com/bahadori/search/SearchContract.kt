package com.bahadori.search

import com.bahadori.metropolitanmuseum.core.designsystem.UnidirectionalViewModel

interface SearchContract : UnidirectionalViewModel<SearchContract.Event, SearchContract.State> {

    data class State(
        val objects: List<com.bahadori.model.MetObject> = listOf(),
        val query: String = "",
        val page: Int = 1,
        val loading: com.bahadori.common.loading.LoadState = com.bahadori.common.loading.LoadState.NotLoading(),
        val endReached: Boolean = false,
    )

    sealed class Event {
        data class OnQueryEntered(val query: String) : Event()
        object OnLoadMore : Event()
    }

}