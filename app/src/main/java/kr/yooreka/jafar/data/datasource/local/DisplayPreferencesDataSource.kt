package kr.yooreka.jafar.data.datasource.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val DISPLAY_PREFS_NAME = "display_preferences"

val Context.displayDataStore: DataStore<Preferences> by preferencesDataStore(
    name = DISPLAY_PREFS_NAME
)

@Singleton
class DisplayPreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    private object Keys {
        val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
        val FONT_SCALE = intPreferencesKey("font_scale") // 1,2,3
        val LANGUAGE = stringPreferencesKey("language")  // "ko","en","ja"
    }

    // --- Read ---

    val isDarkMode: Flow<Boolean> = dataStore.data
        .map { prefs ->
            prefs[Keys.IS_DARK_MODE] ?: false
        }

    val fontScale: Flow<Int> = dataStore.data
        .map { prefs ->
            // 기본값: NORMAL
            prefs[Keys.FONT_SCALE] ?: 2
        }

    val language: Flow<String> = dataStore.data
        .map { prefs ->
            // 기본값: 한국어
            prefs[Keys.LANGUAGE] ?: "ko"
        }

    // --- Write ---

    suspend fun setDarkMode(isDarkMode: Boolean) {
        dataStore.edit { prefs ->
            prefs[Keys.IS_DARK_MODE] = isDarkMode
        }
    }

    suspend fun setFontScale(fontScale: Int) {
        dataStore.edit { prefs ->
            prefs[Keys.FONT_SCALE] = fontScale
        }
    }

    suspend fun setLanguage(language: String) {
        dataStore.edit { prefs ->
            prefs[Keys.LANGUAGE] = language
        }
    }
}
