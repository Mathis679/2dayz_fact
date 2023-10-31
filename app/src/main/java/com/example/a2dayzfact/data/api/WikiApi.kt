package com.example.a2dayzfact.data.api

import com.example.a2dayzfact.data.model.WikiFactListEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface WikiApi {

    @GET("rest_v1/feed/onthisday/events/{month}/{day}")
    suspend fun getFacts(@Path("month") month: String, @Path("day") day: String): WikiFactListEntity

}
