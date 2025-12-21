package kr.yooreka.jafar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kr.yooreka.jafar.domain.model.DisplayVO
import kr.yooreka.jafar.domain.model.FontScale
import kr.yooreka.jafar.domain.model.Language
import kr.yooreka.jafar.domain.repository.DisplayRepository
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val displayRepository: DisplayRepository
) : ViewModel() {
    val displayState: StateFlow<DisplayVO> =
        displayRepository.display
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = DisplayVO( // 기본값은 domain enum 기준으로
                    isDarkMode = false,
                    fontScale = FontScale.NORMAL,
                    language = Language.KOREAN
                )
            )


}