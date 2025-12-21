package kr.yooreka.jafar.data.dto

import kr.yooreka.jafar.domain.model.DisplayVO
import kr.yooreka.jafar.domain.model.FontScale
import kr.yooreka.jafar.domain.model.Language

data class DisplayDTO(
    val isDarkMode: Boolean,
    val fontScale: Int,
    val language: String
) {
    fun toVO(): DisplayVO = DisplayVO(
        isDarkMode = isDarkMode,
        fontScale = when (fontScale) {
            1 -> FontScale.SMALL
            2 -> FontScale.NORMAL
            3 -> FontScale.LARGE
            else -> FontScale.NORMAL
        },
        language = when (language) {
            "ko" -> Language.KOREAN
            "en" -> Language.ENGLISH
            "ja" -> Language.JAPANESE
            else -> Language.KOREAN
        }
    )

    companion object {
        fun from(vo: DisplayVO) = DisplayDTO(
            isDarkMode = vo.isDarkMode,
            fontScale = vo.fontScale.toStorageInt(),
            language = vo.language.toStorageCode(),
        )
    }
}

fun FontScale.toStorageInt() = when (this) {
    FontScale.SMALL -> 1
    FontScale.NORMAL -> 2
    FontScale.LARGE -> 3
}

fun Language.toStorageCode() = when (this) {
    Language.KOREAN -> "ko"
    Language.ENGLISH -> "en"
    Language.JAPANESE -> "ja"
}