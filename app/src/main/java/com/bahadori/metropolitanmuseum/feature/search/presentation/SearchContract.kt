package com.bahadori.metropolitanmuseum.feature.search.presentation

import com.bahadori.metropolitanmuseum.common.loading.LoadState
import com.bahadori.metropolitanmuseum.core.designsystem.UnidirectionalViewModel
import com.bahadori.metropolitanmuseum.model.data.MetObject

interface SearchContract : UnidirectionalViewModel<SearchContract.Event, SearchContract.State> {

    data class State(
        val objects: List<MetObject> = listOf(),
        val query: String = "",
        val page: Int = 1,
        val loading: LoadState = LoadState.NotLoading(),
        val endReached: Boolean = false,
    )

    sealed class Event {
        data class OnQueryEntered(val query: String) : Event()
        object OnLoadMore : Event()
    }

}