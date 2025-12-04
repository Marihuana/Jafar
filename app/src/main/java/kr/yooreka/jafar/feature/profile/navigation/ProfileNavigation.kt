package kr.yooreka.jafar.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kr.yooreka.jafar.feature.profile.ProfileScreen

const val profileRoute = "profile"

fun NavGraphBuilder.profileScreen(
    navController: NavController
) {
    composable(route = profileRoute) {
        //todo router로 변경
        ProfileScreen()
    }
}