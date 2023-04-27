package com.bahadori.network.retrofit

object Endpoints {
    /**
     * separating api version in order to use different versions of api in the future
     */
    private const val V1 = "v1"
    internal const val GET_OBJECTS = "$V1/objects/{objectID}"
    internal const val SEARCH = "$V1/search"
}