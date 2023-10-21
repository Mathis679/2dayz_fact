package com.example.a2dayzfact.ui.screens.fact.content

import com.example.a2dayzfact.data.FactDetailRepository
import com.example.a2dayzfact.di.module.MainDispatcher
import com.example.a2dayzfact.domain.usecase.GetFactsForDayUseCase
import com.example.a2dayzfact.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class FactDetailViewModel @Inject constructor(
    private val factDetailRepository: FactDetailRepository,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
): BaseViewModel(dispatcher) {

    private val _factUiState: MutableStateFlow<FactUiState> = MutableStateFlow(
        FactUiState.Loading
    )
    val factUiState: StateFlow<FactUiState> = _factUiState

    fun loadPage() = launch {
        factDetailRepository.getFromCache()?.let {
            _factUiState.emit(FactUiState.Success(
                fact = it,
                displayDate = getDisplayDateForFact(it)
            ))
        } ?: run {
            _factUiState.emit(FactUiState.Error)
        }
    }

    private suspend fun getDisplayDateForFact(fact: GetFactsForDayUseCase.Fact): String = withContext(dispatcher) {
        val calendar = Calendar.getInstance()
        calendar.set(fact.year, fact.month - 1, fact.day)
        SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(calendar.time)
    }

    sealed class FactUiState {
        object Loading : FactUiState()
        object Error : FactUiState()
        data class Success(
            val fact: GetFactsForDayUseCase.Fact,
            val displayDate: String
        ): FactUiState()
    }
}
