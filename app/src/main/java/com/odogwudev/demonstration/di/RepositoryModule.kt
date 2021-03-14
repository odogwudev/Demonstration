package com.odogwudev.demonstration.di

import android.content.Context
import com.odogwudev.demonstration.repository.MainRepository
import com.odogwudev.demonstration.repository.retrofit.FileService
import com.odogwudev.demonstration.repository.retrofit.MenuItemNetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        @ApplicationContext context: Context,
        fileService: FileService,
        menuItemNetworkMapper: MenuItemNetworkMapper
    ): MainRepository {
        return MainRepository(context, fileService, menuItemNetworkMapper)
    }
}