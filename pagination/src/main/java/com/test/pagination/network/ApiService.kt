package com.test.pagination.network

import com.test.pagination.domain.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/users")
    suspend fun getFakeResponse(
        @Query("page") page:Int,
        @Query("per_page") size:Int
    ): Response<ApiResponse>

}