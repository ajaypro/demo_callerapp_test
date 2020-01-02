package com.source.truecallerapp.network

import com.source.truecallerapp.BuildConfig
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming

interface NetworkService {
    @Streaming
    @GET(BuildConfig.BASE_URL)
    suspend fun doDataCall(): Response<ResponseBody>
}