package com.bahadori.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bahadori.designsystem.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: com.bahadori.domain.MetObjectRepository,
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

            is DetailContract.Event.ShowGallery -> {
                _state.update {
                    it.copy(showGallery = !it.showGallery)
                }
            }
        }
    }


    private fun getObject(objectID: Int) {
        viewModelScope.launch {
            _state.update { it.copy(loading = com.bahadori.common.loading.LoadState.Loading()) }
            val result = repository.getMetObject(objectID)
            if (result.isSuccess) {
                val currentObject = result.getOrNull()
                _state.update {
                    it.copy(
                        currentObject = result.getOrNull(),
                        loading = com.bahadori.common.loading.LoadState.NotLoading(),
                        images = addAllImages(currentObject)
                    )
                }
            } else {
                _state.update {
                    it.copy(loading = com.bahadori.common.loading.LoadState.Error(result.exceptionOrNull()?.message))
                }
            }
        }
    }

    private fun addAllImages(currentObject: com.bahadori.model.MetObject?): List<String> {
        val images = mutableListOf<String>().apply {
            currentObject?.primaryImage?.let { add(it) }
            currentObject?.additionalImages?.let { addAll(it) }
        }
        Log.i("TAG", "addAllImages ${images.size}")
        return images.filterNot { it.isNullOrEmpty() }.toList()
    }
}