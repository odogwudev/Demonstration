package com.odogwudev.demonstration.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.odogwudev.demonstration.repository.retrofit.FileService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
    }

    @Provides
    @Singleton
    fun provideFileService(retrofit: Retrofit.Builder): FileService {
        return retrofit
            .build()
            .create(FileService::class.java)
    }

    companion object {
        const val BASE_URL = "https://www.dropbox.com/"
    }
}