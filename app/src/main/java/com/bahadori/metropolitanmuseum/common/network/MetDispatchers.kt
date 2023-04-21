package com.bahadori.metropolitanmuseum.common.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: MetDispatchers)

enum class MetDispatchers {
    Default,
    IO,
}
