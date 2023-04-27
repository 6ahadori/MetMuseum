package com.bahadori.search

import androidx.lifecycle.viewModelScope
import com.bahadori.designsystem.base.BaseViewModel
import com.bahadori.domain.MetObjectRepository
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

    private val paginator = com.bahadori.common.paging.DefaultPaginator(
        initialKey = state.value.page,
        onLoadUpdated = { loading ->
            if (loading)
                _state.update {
                    it.copy(loading = com.bahadori.common.loading.LoadState.Loading())
                }
        },
        onLoadAllObjects = {
            _state.update {
                it.copy(page = 1, endReached = false)
            }
            metObjectRepository.search(state.value.query.trim())
        },
        onRequest = { ids ->
            metObjectRepository.getMetObjects(*ids.toIntArray())
        },
        getNextKey = {
            state.value.page + 1
        },
        onError = { e ->
            _state.update {
                it.copy(loading = com.bahadori.common.loading.LoadState.Error(e?.message))
            }
        },
        onSuccess = { list, newPage, start ->
            _state.update {
                it.copy(
                    loading = com.bahadori.common.loading.LoadState.NotLoading(),
                    objects = if (start) list else (state.value.objects + list),
                    endReached = list.isEmpty(),
                    page = newPage
                )
            }
        }
    )


    override fun event(event: SearchContract.Event) {
        when (event) {
            is SearchContract.Event.OnQueryEntered -> {
                if (state.value.query != event.query) {
//                    searchJob?.cancel()
                    _state.update {
                        it.copy(query = event.query)
                    }
                    searchJob = viewModelScope.launch {
                        paginator.loadAllObjectIDs()
                    }
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