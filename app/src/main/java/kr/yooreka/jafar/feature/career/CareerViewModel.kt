package kr.yooreka.jafar.feature.career

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kr.yooreka.jafar.domain.model.CompanyVO
import kr.yooreka.jafar.domain.repository.CareerRepository
import javax.inject.Inject

@HiltViewModel
class CareerViewModel @Inject constructor(
    careerRepository: CareerRepository
) : ViewModel(){
    val uiState: StateFlow<CareerUiState> =
        careerRepository
            .companies                      // Flow<List<CompanyVO>>
            .map { companies ->
                CareerUiState(
                    isLoading = false,
                    companies = companies.map(CompanyVO::toUiState)
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CareerUiState(isLoading = true)
            )


}