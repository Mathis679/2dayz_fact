package com.example.a2dayzfact.domain.usecase

import com.example.a2dayzfact.data.FactsRepository
import com.example.a2dayzfact.di.module.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFactsForDayUseCase @Inject constructor(
    private val factsRepository: FactsRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(day: Int, month: Int) : List<Fact> = withContext(dispatcher) {
        factsRepository.getFactsFromWiki(day, month).mapNotNull { model ->
            try {
                Fact(
                    year = model.year,
                    month = month,
                    day = day,
                    title = model.title,
                    content = model.pages[0].content,
                    source = model.pages[0].urls.mobile.source,
                    image = model.pages[0].thumbnail.source
                )
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    data class Fact(
        val year: Int,
        val month: Int,
        val day: Int,
        val title: String,
        val content: String,
        val source: String,
        val image: String
    )
}
