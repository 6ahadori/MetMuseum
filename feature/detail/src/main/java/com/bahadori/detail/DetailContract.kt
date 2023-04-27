package com.bahadori.detail

import com.bahadori.metropolitanmuseum.core.designsystem.UnidirectionalViewModel

interface DetailContract : UnidirectionalViewModel<DetailContract.Event, DetailContract.State> {

    data class State(
        val currentObject: com.bahadori.model.MetObject? = null,
        val loading: com.bahadori.common.loading.LoadState = com.bahadori.common.loading.LoadState.Loading(false),
        val showGallery: Boolean = false,
        val images: List<String> = emptyList()
    )

    sealed class Event {
        data class GetObject(val objectID: Int) : Event()
        object ShowGallery : Event()
    }

}