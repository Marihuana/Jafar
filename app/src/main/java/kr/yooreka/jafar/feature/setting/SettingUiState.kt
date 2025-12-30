package kr.yooreka.jafar.feature.setting

import androidx.annotation.StringRes
import kr.yooreka.jafar.R
import kr.yooreka.jafar.domain.model.FontScale
import kr.yooreka.jafar.domain.model.Language
import kr.yooreka.jafar.domain.model.SettingVO

data class SettingUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val premium: PremiumUiState = PremiumUiState(),
    val display: DisplayUiState = DisplayUiState(),
    val appVersion: AppInfoUiState = AppInfoUiState()
) {
    companion object {
        fun from(settingVO: SettingVO): SettingUiState {
            return SettingUiState(
                premium = PremiumUiState(
                    isPremium = settingVO.premium.isPremium
                ),
                display = DisplayUiState(
                    isDarkMode = settingVO.display.isDarkMode,
                    fontSize = settingVO.display.fontScale,
                    language = settingVO.display.language
                ),
                appVersion = AppInfoUiState(
                    appVersion = settingVO.appInfo.appVersion
                )
            )
        }
    }
}

data class PremiumUiState(
    val isPremium: Boolean = false
)

data class DisplayUiState(
    val isDarkMode: Boolean = false,
    val fontSize: FontScale = FontScale.NORMAL,
    val language: Language = Language.KOREAN,
)

data class AppInfoUiState(
    val appVersion: String = "1.0.0"
)


@StringRes
fun FontScale.toLabelResId(): Int {
    return when (this) {
        FontScale.SMALL -> R.string.setting_font_scale_small
        FontScale.NORMAL -> R.string.setting_font_scale_medium
        FontScale.LARGE -> R.string.setting_font_scale_large
    }
}

@StringRes
fun Language.toLabelResId(): Int {
    return when (this) {
        Language.KOREAN -> R.string.setting_language_kr
        Language.ENGLISH -> R.string.setting_language_en
        Language.JAPANESE -> R.string.setting_language_jp
    }
}