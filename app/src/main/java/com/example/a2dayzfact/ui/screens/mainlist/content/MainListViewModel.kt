package com.example.a2dayzfact.ui.screens.mainlist.content

import com.example.a2dayzfact.data.FactDetailRepository
import com.example.a2dayzfact.di.module.MainDispatcher
import com.example.a2dayzfact.domain.usecase.GetFactsForDayUseCase
import com.example.a2dayzfact.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    private val getFactsForDayUseCase: GetFactsForDayUseCase,
    private val factDetailRepository: FactDetailRepository,
    @MainDispatcher dispatcher: CoroutineDispatcher
): BaseViewModel(dispatcher) {

    private val _mainListUiState: MutableStateFlow<MainListUiState> = MutableStateFlow(
        MainListUiState(
            nextDay = "",
            previousDay = "",
            currentDay = ""
        )
    )
    val mainListUiState: StateFlow<MainListUiState> = _mainListUiState

    private val _factsUiState: MutableStateFlow<FactsUiState> = MutableStateFlow(FactsUiState.Loading)
    val factsUiState: StateFlow<FactsUiState> = _factsUiState

    private var currentDay = Calendar.getInstance()

    init {
        loadPage()
    }

    fun nextDay() = launch {
        currentDay.add(Calendar.DAY_OF_MONTH, 1)
        loadPage()
    }

    fun previousDay() = launch {
        currentDay.add(Calendar.DAY_OF_MONTH, -1)
        loadPage()
    }

    fun clickOnFact(fact: GetFactsForDayUseCase.Fact) = launch {
        factDetailRepository.saveInCache(fact)
    }

    private fun getDayString(calendar: Calendar) : String {
        return SimpleDateFormat("dd MMMM", Locale.getDefault()).format(calendar.time)
    }

    private fun loadPage() = launch {
        val nextDay = Calendar.getInstance().apply {
            time = currentDay.time
            add(Calendar.DAY_OF_MONTH, 1)
        }
        val previousDay = Calendar.getInstance().apply {
            time = currentDay.time
            add(Calendar.DAY_OF_MONTH, -1)
        }
        _mainListUiState.emit(
            MainListUiState(
                nextDay = getDayString(nextDay),
                previousDay = getDayString(previousDay),
                currentDay = getDayString(currentDay)
            )
        )
        _factsUiState.emit(FactsUiState.Loading)
        _factsUiState.emit(
            FactsUiState.Success(
                getFactsForDayUseCase(
                    day = currentDay.get(Calendar.DAY_OF_MONTH),
                    month = currentDay.get(Calendar.MONTH) + 1
                )
            )
        )
    }

    data class MainListUiState(
        val nextDay: String,
        val previousDay: String,
        val currentDay: String
    )

    sealed class FactsUiState {
        object Loading : FactsUiState()
        object Error : FactsUiState()
        data class Success(
            val facts: List<GetFactsForDayUseCase.Fact>
        ): FactsUiState()
    }

}
