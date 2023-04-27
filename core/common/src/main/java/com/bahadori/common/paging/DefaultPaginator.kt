package com.bahadori.common.paging

/**
 * This is a helper class for handling pagination
 * @param initialKey is for the initial key
 * @param onLoadUpdated if loading or not
 * @param onLoadAllObjects to get all ids on search action
 * @param onRequest to get objects
 * @param getNextKey getting next page
 * @param onError when an error occurred
 * @param onSuccess when the data retrieved successfully
 */
class DefaultPaginator<Item>(
    private val initialKey: Int,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onLoadAllObjects: suspend () -> List<Int>,
    private inline val onRequest: suspend (nextKey: List<Int>) -> Result<List<Item>>,
    private inline val getNextKey: suspend (List<Item>) -> Int,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Int, start: Boolean) -> Unit
) : Paginator<Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false
    private var objectIDs = emptyList<Int>()

    override suspend fun loadAllObjectIDs() {
        onLoadUpdated(true)
        objectIDs = onLoadAllObjects()
        onLoadUpdated(false)
        reset()
        loadNextItems()
    }

    override suspend fun loadNextItems() {
        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(
            objectIDs.subList(getRange().first, getRange().last)
        )
        isMakingRequest = false
        val items = result.getOrElse {
            onError(it)
            onLoadUpdated(false)
            return
        }
        val start = initialKey == currentKey
        currentKey = getNextKey(items)
        onSuccess(items, currentKey, start)
        onLoadUpdated(false)
    }

    override fun reset() {
        currentKey = initialKey
    }

    private fun getRange(): IntRange {
        val from = when {
            objectIDs.isEmpty() -> 0
            objectIDs.size > (currentKey - 1) * PAGE_SIZE -> (currentKey - 1) * PAGE_SIZE
            else -> objectIDs.size
        }

        val to = when {
            objectIDs.isEmpty() -> 0
            objectIDs.size > currentKey * PAGE_SIZE -> (currentKey * PAGE_SIZE)
            else -> objectIDs.size
        }
        return IntRange(from, to)
    }

    companion object {
        private const val PAGE_SIZE = 10
    }
}