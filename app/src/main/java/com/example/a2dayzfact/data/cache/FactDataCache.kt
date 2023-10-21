package com.example.a2dayzfact.data.cache

import com.example.a2dayzfact.di.module.IoDispatcher
import com.example.a2dayzfact.domain.usecase.GetFactsForDayUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FactDataCache @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    private var dataCache: GetFactsForDayUseCase.Fact? = null

    suspend fun saveDataCache(fact: GetFactsForDayUseCase.Fact) = withContext(dispatcher) {
        dataCache = fact
    }

    suspend fun getDataCache(): GetFactsForDayUseCase.Fact? = withContext(dispatcher) {
        dataCache
    }
}
