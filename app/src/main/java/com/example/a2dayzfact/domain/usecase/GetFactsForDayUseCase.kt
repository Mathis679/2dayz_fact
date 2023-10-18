package com.example.a2dayzfact.domain.usecase

import com.example.a2dayzfact.di.module.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

class GetFactsForDayUseCase @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(day: Int, month: Int) : List<Fact> = withContext(dispatcher) {
        // TODO call repository
        delay(1000)
        listOf(
            randomFact(),
            randomFact(),
            randomFact(),
            randomFact(),
            randomFact()
        )
    }

    private suspend fun randomFact(): Fact = withContext(dispatcher) {
        Fact(
            year = Random(2022).nextInt(),
            title = "French and British forces bombard Sevastopol for the first time during the Crimean War",
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla id turpis bibendum ipsum consectetur vulputate nec viverra enim. Ut vitae velit urna. Quisque non libero nulla. Quisque orci purus, imperdiet id est sit amet, sodales tempor magna. Aenean libero magna, tempus vitae orci sit amet, pellentesque accumsan ipsum. Proin vitae ligula sollicitudin, suscipit nulla eget, venenatis felis. Ut et pharetra urna. Ut aliquam, urna ut ultrices aliquam, tellus nisl hendrerit justo, id venenatis quam risus a turpis. Aliquam dapibus orci sed arcu rhoncus, sed congue orci ornare. Vivamus nulla nibh, porta congue nibh non, luctus vulputate neque. Mauris a dui eu turpis dignissim ultrices dapibus sed urna. Integer consequat purus velit, sit amet luctus tellus pellentesque sed. Nam rhoncus nulla velit, ut ultrices est ultricies ut. Nullam et ligula quis arcu venenatis sodales eget vitae tellus. Morbi neque odio, ultricies sit amet libero ut, auctor ultricies ante. Integer ullamcorper ligula ligula, non euismod nunc vulputate id. Mauris eu accumsan ante. Donec viverra tempor rhoncus.",
            image = "https://picsum.photos/230/100"
        )
    }

    data class Fact(
        val year: Int,
        val title: String,
        val content: String,
        val image: String
    )
}
