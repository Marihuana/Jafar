package kr.yooreka.jafar.feature.setting

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingUiState(isLoading = true))
    val uiState: StateFlow<SettingUiState> = _uiState
}