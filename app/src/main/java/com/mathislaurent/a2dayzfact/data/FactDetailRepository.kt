package com.mathislaurent.a2dayzfact.data

import com.mathislaurent.a2dayzfact.data.cache.FactDataCache
import com.mathislaurent.a2dayzfact.di.module.IoDispatcher
import com.mathislaurent.a2dayzfact.domain.usecase.GetFactsForDayUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FactDetailRepository@Inject constructor(
    private val factDataCache: FactDataCache,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun saveInCache(fact: GetFactsForDayUseCase.Fact) = withContext(dispatcher) {
        factDataCache.saveDataCache(fact)
    }

    suspend fun getFromCache(): GetFactsForDayUseCase.Fact? = withContext(dispatcher) {
        factDataCache.getDataCache()
    }
}
