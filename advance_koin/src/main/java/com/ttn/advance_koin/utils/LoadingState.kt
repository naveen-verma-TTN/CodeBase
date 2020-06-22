package com.ttn.advance_koin.utils

/*
 * Created by Naveen Verma on 22/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

@Suppress("DataClassPrivateConstructor")
data class LoadingState private constructor(val status: Status, val msg: String? = null) {
    companion object {
        val LOADED = LoadingState(Status.SUCCESS)
        val LOADING = LoadingState(Status.RUNNING)
        fun error(msg: String?) = LoadingState(Status.FAILED, msg)
    }

    enum class Status {
        RUNNING,
        SUCCESS,
        FAILED
    }
}