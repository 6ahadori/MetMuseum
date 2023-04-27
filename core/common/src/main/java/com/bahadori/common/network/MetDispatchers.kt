package com.bahadori.common.network

import javax.inject.Qualifier

/**
 * Dispatcher Annotation
 * @sample
 *    @Dispatcher(IO) ioDispatcher: CoroutineDispatcher
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: MetDispatchers)

enum class MetDispatchers {
    Default,
    IO,
}
