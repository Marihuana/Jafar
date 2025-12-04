package kr.yooreka.jafar.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kr.yooreka.jafar.feature.career.navigation.careerScreen
import kr.yooreka.jafar.feature.portfolio.navigation.portfolioScreen
import kr.yooreka.jafar.feature.profile.navigation.profileScreen
import kr.yooreka.jafar.feature.setting.navigation.settingScreen

@Composable
fun JafarNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = TopLevelNavItem.PROFILE.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        profileScreen(navController)
        careerScreen(navController)
        portfolioScreen(navController)
        settingScreen(navController)
    }
}