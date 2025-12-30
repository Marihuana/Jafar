package kr.yooreka.jafar.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kr.yooreka.jafar.feature.career.navigation.careerScreen
import kr.yooreka.jafar.feature.portfolio.navigation.portfolioScreen
import kr.yooreka.jafar.feature.profile.navigation.profileScreen
import kr.yooreka.jafar.feature.setting.navigation.settingScreen

private val TabOrder = mapOf(
    TopLevelNavItem.PROFILE.route to 0,
    TopLevelNavItem.CAREER.route to 1,
    TopLevelNavItem.PORTFOLIO.route to 2,
    TopLevelNavItem.SETTING.route to 3,
)

private const val TAB_ANIM_DURATION_MS = 220
private const val TAB_OFFSET_DIVISOR = 6

private fun NavBackStackEntry.isTopLevel(): Boolean =
    destination.route in TabOrder

private fun NavBackStackEntry.order(): Int =
    TabOrder[destination.route] ?: 0

@Composable
fun JafarNavHost(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    startDestination: String = TopLevelNavItem.PROFILE.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = {
            // Slide only when switching between top-level tabs.
            if (initialState.isTopLevel() && targetState.isTopLevel()) {
                val forward = targetState.order() > initialState.order()
                val offset: (Int) -> Int = { fullWidth ->
                    val delta = fullWidth / TAB_OFFSET_DIVISOR
                    if (forward) delta else -delta
                }
                slideInHorizontally(
                    animationSpec = tween(TAB_ANIM_DURATION_MS),
                    initialOffsetX = offset
                ) + fadeIn(tween(TAB_ANIM_DURATION_MS))
            } else {
                // For in-tab navigation, keep it subtle (fade only).
                fadeIn(tween(150))
            }
        },
        exitTransition = {
            if (initialState.isTopLevel() && targetState.isTopLevel()) {
                val forward = targetState.order() > initialState.order()
                val offset: (Int) -> Int = { fullWidth ->
                    val delta = fullWidth / TAB_OFFSET_DIVISOR
                    if (forward) -delta else delta
                }
                slideOutHorizontally(
                    animationSpec = tween(TAB_ANIM_DURATION_MS),
                    targetOffsetX = offset
                ) + fadeOut(tween(TAB_ANIM_DURATION_MS))
            } else {
                fadeOut(tween(150))
            }
        },
        popEnterTransition = { fadeIn(tween(150)) },
        popExitTransition = { fadeOut(tween(150)) },
    ) {
        profileScreen(navController)
        careerScreen(navController)
        portfolioScreen(navController, snackbarHostState)
        settingScreen(navController)
    }
}