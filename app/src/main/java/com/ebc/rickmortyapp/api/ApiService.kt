package com.ebc.rickmortyapp.api


import com.ebc.rickmortyapp.models.ResponseApi
import com.ebc.rickmortyapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.END_POINT)
    suspend fun getAllCharacters(
        @Query("page") page:Int
    ):Response<ResponseApi>

}