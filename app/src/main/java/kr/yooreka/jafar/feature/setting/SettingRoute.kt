package kr.yooreka.jafar.feature.setting

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun SettingRoute(
    viewModel: SettingViewModel = hiltViewModel()
) {
    val uiState by viewModel.settingUiState.collectAsState()

    SettingScreen(
        uiState = uiState,
        onDarkModeToggle = viewModel::onDarkModeToggle,
        onFontSizeClick = viewModel::onFontSizeClick,
        onLanguageClick = viewModel::onLanguageClick,
        onUpgradeClick = viewModel::onUpgradeClick
    )
}