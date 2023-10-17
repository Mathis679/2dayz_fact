package com.example.a2dayzfact.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2dayzfact.di.module.MainDispatcher
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class BaseViewModel(
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel(), CoroutineScope {

    private val exceptionHandler = CoroutineExceptionHandler { _, error ->
        hideLoader()
        if (error !is CancellationException) {
            displayError(error)
        }
    }

    private val parentJob = SupervisorJob() + exceptionHandler
    override val coroutineContext: CoroutineContext
        get() = dispatcher + parentJob

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _displayError = MutableSharedFlow<Throwable>()
    val displayError = _displayError.asSharedFlow()

    protected suspend fun withLoading(block: suspend () -> Unit) {
        displayLoader()
        block()
        hideLoader()
    }

    private fun displayLoader() {
        _isLoading.postValue(true)
    }

    private fun hideLoader() {
        _isLoading.postValue(false)
    }

    open fun displayError(error: Throwable) {
        launch {
            _displayError.emit(error)
        }
    }

    override fun onCleared() {
        this.parentJob.cancel()
        super.onCleared()
    }
}
