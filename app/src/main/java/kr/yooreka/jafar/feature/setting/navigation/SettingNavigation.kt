package kr.yooreka.jafar.feature.setting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kr.yooreka.jafar.feature.setting.SettingScreen

const val settingRoute = "setting"

fun NavGraphBuilder.settingScreen(
    navController: NavController
) {
    composable(route = settingRoute) {
        //todo router로 변경
        SettingScreen()
    }
}