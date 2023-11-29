package com.mathislaurent.a2dayzfact.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private const val BASE_WIKI_SCHEME_URL = "https://"
    private const val BASE_WIKI_DOMAIN_URL = ".wikipedia.org/api/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val lang = Locale.getDefault().language
        val baseUrl = StringBuilder()
            .append(BASE_WIKI_SCHEME_URL)
            .append(lang)
            .append(BASE_WIKI_DOMAIN_URL)
            .toString()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
