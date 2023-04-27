package com.bahadori.common.loading

/**
 * This is a helper class to handle loading/error events that contains 3 states:
 * Loading: when data is loading, and also there is a flag if loading is refreshing.
 * NotLoading: when data is succeeded or failed and not loading anymore.
 * Error: when an error occurred.
 */
sealed class LoadState {
    data class Loading(val refresh: Boolean = false) : LoadState()
    data class NotLoading(val nothing: Unit = Unit) : LoadState()
    data class Error(val message: String? = null) : LoadState() {
        override fun equals(other: Any?): Boolean {
            return false
        }

        override fun hashCode(): Int {
            return message?.hashCode() ?: 0
        }
    }

    sealed class Type {
        object Append : Type()
        object Prepared : Type()
        object Refresh : Type()
    }
}

inline val LoadState.refreshing: Boolean get() = this is LoadState.Loading && refresh

inline val LoadState.justLoading: Boolean get() = this is LoadState.Loading && !refresh
