package kr.yooreka.jafar.feature.portfolio.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kr.yooreka.jafar.feature.portfolio.PortfolioRoute

const val portfolioRoute = "portfolio"

fun NavGraphBuilder.portfolioScreen(
    navController: NavController
) {
    composable(route = portfolioRoute) {
        PortfolioRoute()
    }
}