package com.example.catsapp.data

import com.example.catsapp.data.CatsResponse
import retrofit2.http.GET

interface CatsApi {

    @GET("/v1/breeds/")
    suspend fun fetchCats(): List<CatsResponse>
}
