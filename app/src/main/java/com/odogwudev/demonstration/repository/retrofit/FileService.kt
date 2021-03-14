package com.odogwudev.demonstration.repository.retrofit

import okhttp3.ResponseBody
import retrofit2.http.GET

interface FileService {
    @GET("s/fk3d5kg6cptkpr6/menu.json?dl=1")
    suspend fun getJSONObject(): ResponseBody
}