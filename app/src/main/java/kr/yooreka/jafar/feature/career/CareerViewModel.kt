package kr.yooreka.jafar.feature.career

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.yooreka.jafar.domain.repository.CareerRepository
import javax.inject.Inject

@HiltViewModel
class CareerViewModel @Inject constructor(
    private val careerRepository: CareerRepository
) : ViewModel(){
    private val _uiState = MutableStateFlow(CareerUiState(isLoading = true))
    val uiState: StateFlow<CareerUiState> = _uiState



}