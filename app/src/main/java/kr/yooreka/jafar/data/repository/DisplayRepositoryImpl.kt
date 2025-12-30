package kr.yooreka.jafar.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kr.yooreka.jafar.data.datasource.local.DisplayPreferencesDataSource
import kr.yooreka.jafar.data.dto.DisplayDTO
import kr.yooreka.jafar.data.dto.toStorageCode
import kr.yooreka.jafar.data.dto.toStorageInt
import kr.yooreka.jafar.domain.model.DisplayVO
import kr.yooreka.jafar.domain.model.FontScale
import kr.yooreka.jafar.domain.model.Language
import kr.yooreka.jafar.domain.repository.DisplayRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DisplayRepositoryImpl @Inject constructor(
    private val localDataSource: DisplayPreferencesDataSource
) : DisplayRepository {

    override val display: Flow<DisplayVO> =
        combine(
            localDataSource.isDarkMode,
            localDataSource.fontScale,
            localDataSource.language
        ) { isDarkMode, fontScaleInt, languageCode ->
            DisplayDTO(
                isDarkMode = isDarkMode,
                fontScale = fontScaleInt,
                language = languageCode
            ).toVO()
        }.distinctUntilChanged()

    override suspend fun setDarkMode(isDarkMode: Boolean) {
        localDataSource.setDarkMode(isDarkMode)
    }

    override suspend fun setFontScale(fontScale: FontScale) {
        localDataSource.setFontScale(fontScale.toStorageInt())
    }

    override suspend fun setLanguage(language: Language) {
        localDataSource.setLanguage(language.toStorageCode())
    }
}