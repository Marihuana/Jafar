package kr.yooreka.jafar.feature.setting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kr.yooreka.jafar.feature.setting.SettingRoute

const val settingRoute = "setting"

fun NavGraphBuilder.settingScreen(
    navController: NavController
) {
    composable(route = settingRoute) {
        SettingRoute()
    }
}