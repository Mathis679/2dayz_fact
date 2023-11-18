package com.mathislaurent.a2dayzfact.data

import com.mathislaurent.a2dayzfact.data.api.WikiApi
import com.mathislaurent.a2dayzfact.data.model.WikiFactEntity
import com.mathislaurent.a2dayzfact.di.module.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FactsRepository @Inject constructor(
    private val wikiApi: WikiApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun getFactsFromWiki(day: Int, month: Int): List<WikiFactEntity> = withContext(dispatcher) {
        wikiApi.getFacts(
            month = "${if (month < 10) "0$month" else month}",
            day = "${if (day < 10) "0$day" else day}"
        ).facts
    }
}
