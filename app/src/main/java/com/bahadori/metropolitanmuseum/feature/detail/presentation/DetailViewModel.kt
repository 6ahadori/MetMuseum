package com.bahadori.metropolitanmuseum.feature.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bahadori.metropolitanmuseum.common.loading.LoadState
import com.bahadori.metropolitanmuseum.core.designsystem.base.BaseViewModel
import com.bahadori.metropolitanmuseum.feature.search.domain.repository.MetObjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MetObjectRepository,
    stateHandle: SavedStateHandle
) : BaseViewModel(), DetailContract {

    private val _state = MutableStateFlow(DetailContract.State())
    override val state: StateFlow<DetailContract.State> = _state.asStateFlow()

    init {
        stateHandle.get<Int>("objectID")?.let { objectID ->
            if (objectID != -1)
                event(DetailContract.Event.GetObject(objectID))
        }
    }

    override fun event(event: DetailContract.Event) {
        when (event) {
            is DetailContract.Event.GetObject -> {
                getObject(event.objectID)
            }
        }
    }


    private fun getObject(objectID: Int) {
        viewModelScope.launch {
            _state.update { it.copy(loading = LoadState.Loading()) }
            val result = repository.getMetObject(objectID)
            if (result.isSuccess) {
                _state.update {
                    it.copy(currentObject = result.getOrNull(), loading = LoadState.NotLoading())
                }
            } else {
                _state.update {
                    it.copy(loading = LoadState.Error(result.exceptionOrNull()?.message))
                }
            }
        }
    }
}