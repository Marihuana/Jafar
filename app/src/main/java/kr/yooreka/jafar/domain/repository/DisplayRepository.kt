package kr.yooreka.jafar.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.yooreka.jafar.domain.model.DisplayVO
import kr.yooreka.jafar.domain.model.FontScale
import kr.yooreka.jafar.domain.model.Language

interface DisplayRepository {
    val display: Flow<DisplayVO>

    suspend fun setDarkMode(isDarkMode: Boolean)
    suspend fun setFontScale(fontScale: FontScale)
    suspend fun setLanguage(language: Language)
}