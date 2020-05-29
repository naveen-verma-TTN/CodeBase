package com.ttn.retrofit_kotlin.services

import com.ttn.retrofit_kotlin.models.Destination
import retrofit2.Call
import retrofit2.http.*

interface DestinationService {

    // get list of destination -- using filters -- custom headers

//    @Headers("x-device-type: Android")

    @GET("destination")
    fun getDestinationList(
        @QueryMap filter: HashMap<String, String>?
//        @Header("Accept-Language") language: String
    ): Call<List<Destination>>

    // get particular destination
    @GET("destination/{id}")
    fun getDestination(@Path("id") id: Int): Call<Destination>

    // add destination
    @POST("destination")
    fun addDestination(@Body newDestination: Destination): Call<Destination>

    // update destination
    @FormUrlEncoded
    @PUT("destination/{id}")
    fun updateDestination(
        @Path("id") id: Int,
        @Field("city") city: String,
        @Field("description") description: String,
        @Field("country") country: String
    ): Call<Destination>

    // delete destination
    @DELETE("destination/{id}")
    fun deleteDestination(@Path("id") id: Int): Call<Unit>
}