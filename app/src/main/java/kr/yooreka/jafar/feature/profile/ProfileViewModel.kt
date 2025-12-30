package kr.yooreka.jafar.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kr.yooreka.jafar.domain.model.ProfileVO
import kr.yooreka.jafar.domain.repository.ProfileRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState(isLoading = true))
    val uiState: StateFlow<ProfileUiState> = _uiState

    private val _effect = MutableSharedFlow<ProfileEffect>()
    val effect: SharedFlow<ProfileEffect> = _effect

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result: Result<ProfileVO> = repository.getProfile()
            _uiState.value = result.fold(
                onSuccess = { profileVO ->
                    profileVO.toProfileUiState()
                },
                onFailure = { throwable ->
                    _uiState.value.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: "프로필을 불러오지 못했습니다."
                    )
                }
            )
        }
    }

    fun onMailClick() {
        val contact = uiState.value.contact ?: return
        val mail = contact.mail.takeIf { it.isNotBlank() } ?: return

        viewModelScope.launch {
            _effect.emit(ProfileEffect.OpenMail(mail))
        }
    }

    fun onLinkedinClick() {
        val contact = uiState.value.contact ?: return
        val url = contact.linkedin.takeIf { it.isNotBlank() } ?: return

        viewModelScope.launch {
            _effect.emit(ProfileEffect.OpenUrl(url))
        }
    }

    fun onGithubClick() {
        val contact = uiState.value.contact ?: return
        val url = contact.github.takeIf { it.isNotBlank() } ?: return

        viewModelScope.launch {
            _effect.emit(ProfileEffect.OpenUrl(url))
        }
    }
}