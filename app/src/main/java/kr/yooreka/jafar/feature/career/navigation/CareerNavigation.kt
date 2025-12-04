package kr.yooreka.jafar.feature.career.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kr.yooreka.jafar.feature.career.CareerScreen

const val careerRoute = "career"

fun NavGraphBuilder.careerScreen(
    navController: NavController
) {
    composable(route = careerRoute) {
        //todo router로 변경
        CareerScreen()
    }
}