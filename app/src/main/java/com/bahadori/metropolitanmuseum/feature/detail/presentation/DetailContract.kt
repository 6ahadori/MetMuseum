package com.bahadori.metropolitanmuseum.feature.detail.presentation

import com.bahadori.metropolitanmuseum.common.loading.LoadState
import com.bahadori.metropolitanmuseum.core.designsystem.UnidirectionalViewModel
import com.bahadori.metropolitanmuseum.model.data.MetObject

interface DetailContract : UnidirectionalViewModel<DetailContract.Event, DetailContract.State> {

    data class State(
        val currentObject: MetObject? = null,
        val loading: LoadState = LoadState.Loading(false),
    )

    sealed class Event {
        data class GetObject(val objectID: Int) : Event()
    }

}