package kr.yooreka.jafar.feature.portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kr.yooreka.jafar.domain.repository.PortfolioRepository
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val repository: PortfolioRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(PortfolioUiState(isLoading = false))
    val uiState: StateFlow<PortfolioUiState> = _uiState.asStateFlow()

    init {
        loadPortfolio() //load once
    }

    private fun loadPortfolio() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val modulesDeferred = async { repository.getModules() }
            val summaryDeferred = async { repository.getSummary() }

            val modulesResult = modulesDeferred.await()
            val summaryResult = summaryDeferred.await()

            val error = modulesResult.exceptionOrNull() ?: summaryResult.exceptionOrNull()
            if (error != null) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.message,
                        module = ModuleUiState(emptyList()),
                        summary = SummaryUiState(0, 0, 0),
                    )
                }
                return@launch
            }

            _uiState.update {
                it.copy(
                    isLoading = false,
                    errorMessage = null,
                    module = modulesResult.getOrThrow().toUiState { InstallState.NotInstalled },
                    summary = summaryResult.getOrThrow().toUiState(),
                )
            }
        }
    }
}