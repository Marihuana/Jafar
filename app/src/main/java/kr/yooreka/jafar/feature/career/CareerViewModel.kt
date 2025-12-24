package kr.yooreka.jafar.feature.career

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kr.yooreka.jafar.domain.repository.CareerRepository
import javax.inject.Inject

@HiltViewModel
class CareerViewModel @Inject constructor(
    careerRepository: CareerRepository
) : ViewModel() {

    private val _expandedCompanyIds = MutableStateFlow<Set<Long>>(emptySet())

    val uiState: StateFlow<CareerUiState>

    init {
        val companiesFlow = careerRepository.companies

        uiState = combine(
            companiesFlow,
            _expandedCompanyIds
        ) { companies, expandedIds ->
            CareerUiState(
                companies = companies.map { it.toUiState() },
                expandedCompanyIds = expandedIds
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CareerUiState(isLoading = true)
        )

        companiesFlow.onEach { companies ->
            companies.firstOrNull()?.id?.let { firstId ->
                _expandedCompanyIds.update { setOf(firstId) }
            }
        }.launchIn(viewModelScope)
    }

    fun toggleCompanyExpanded(companyId: Long) {
        _expandedCompanyIds.update {
            if (it.contains(companyId)) {
                it - companyId
            } else {
                it + companyId
            }
        }
    }
}