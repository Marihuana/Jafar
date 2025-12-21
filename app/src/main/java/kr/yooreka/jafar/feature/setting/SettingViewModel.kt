package kr.yooreka.jafar.feature.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kr.yooreka.jafar.domain.model.FontScale
import kr.yooreka.jafar.domain.model.Language
import kr.yooreka.jafar.domain.repository.DisplayRepository
import kr.yooreka.jafar.domain.usecase.GetSettingInfoUseCase
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getSettingInfoUseCase: GetSettingInfoUseCase,
    private val displayRepository: DisplayRepository
) : ViewModel() {
    val settingUiState: StateFlow<SettingUiState> =
        getSettingInfoUseCase()
            .map { settingVO -> SettingUiState.from(settingVO) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SettingUiState()
            )

    fun onDarkModeToggle(isDarkMode: Boolean) {
        viewModelScope.launch {
            displayRepository.setDarkMode(isDarkMode = isDarkMode)
        }
    }

    fun onFontSizeClick(fontSize: FontScale) {
        viewModelScope.launch {
            displayRepository.setFontScale(fontScale = fontSize)
        }
    }

    fun onLanguageClick(language: Language) {
        viewModelScope.launch {
            displayRepository.setLanguage(language = language)
        }
    }

    fun onUpgradeClick() {
        //todo api 연동  시 결제도 추가하기
    }

}