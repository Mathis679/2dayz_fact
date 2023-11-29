package com.mathislaurent.a2dayzfact.di.module

import com.mathislaurent.a2dayzfact.data.api.WikiApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {

    @Provides
    @Singleton
    fun provideWikiApi(retrofit: Retrofit): WikiApi {
        return retrofit.create()
    }
}
