package com.source.truecallerapp.data

import com.source.truecallerapp.BuildConfig
import com.source.truecallerapp.network.Networking
import okhttp3.ResponseBody
import retrofit2.Response

class DataSource {

    suspend fun fetchData(): Response<ResponseBody> {
        return Networking.create(BuildConfig.BASE_URL).doDataCall()
    }

}