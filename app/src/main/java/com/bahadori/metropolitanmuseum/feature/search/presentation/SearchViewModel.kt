package com.bahadori.metropolitanmuseum.feature.search.presentation

import androidx.lifecycle.viewModelScope
import com.bahadori.metropolitanmuseum.common.loading.LoadState
import com.bahadori.metropolitanmuseum.common.paging.DefaultPaginator
import com.bahadori.metropolitanmuseum.core.designsystem.base.BaseViewModel
import com.bahadori.metropolitanmuseum.feature.search.domain.repository.MetObjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val metObjectRepository: MetObjectRepository
) : BaseViewModel(), SearchContract {

    private val _state = MutableStateFlow(SearchContract.State())
    override val state: StateFlow<SearchContract.State> = _state.asStateFlow()

    private var searchJob: Job? = null

    private val paginator = DefaultPaginator(
        initialKey = state.value.page,
        onLoadUpdated = { loading ->
            if (loading)
                _state.update {
                    it.copy(loading = LoadState.Loading())
                }
        },
        onLoadAllObjects = {
            _state.update {
                it.copy(page = 1)
            }
            metObjectRepository.search(state.value.query)
        },
        onRequest = { ids ->
            metObjectRepository.getMetObjects(*ids.toIntArray())
        },
        getNextKey = {
            state.value.page + 1
        },
        onError = { e ->
            _state.update {
                it.copy(loading = LoadState.Error(e?.message))
            }
        },
        onSuccess = { list, newPage, start ->
            _state.update {
                it.copy(
                    loading = LoadState.NotLoading(),
                    objects = if (start) list else (state.value.objects + list),
                    endReached = list.isEmpty(),
                    page = newPage
                )
            }
        }
    )


    override fun event(event: SearchContract.Event) {
        when (event) {
            is SearchContract.Event.OnObjectClicked -> TODO()
            is SearchContract.Event.OnQueryEntered -> {
                searchJob?.cancel()
                _state.update {
                    it.copy(query = event.query)
                }
                searchJob = viewModelScope.launch {
                    paginator.loadAllObjectIDs()
                }
            }

            is SearchContract.Event.OnLoadMore -> {
                viewModelScope.launch {
                    paginator.loadNextItems()
                }
            }
        }
    }
}