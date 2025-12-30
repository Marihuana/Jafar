package kr.yooreka.jafar.domain.model

data class SettingVO(
    val premium: PremiumVO,
    val display: DisplayVO,
    val appInfo: AppInfoVO
)

data class PremiumVO(
    val isPremium: Boolean
)

data class DisplayVO(
    val isDarkMode: Boolean,
    val fontScale: FontScale,
    val language: Language
)

data class AppInfoVO(
    val appVersion: String
)


enum class FontScale {
    SMALL,
    NORMAL,
    LARGE
}

enum class Language {
    KOREAN,
    ENGLISH,
    JAPANESE
}
