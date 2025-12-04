package kr.yooreka.jafar.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kr.yooreka.jafar.R
import kr.yooreka.jafar.feature.career.navigation.careerRoute
import kr.yooreka.jafar.feature.portfolio.navigation.portfolioRoute
import kr.yooreka.jafar.feature.profile.navigation.profileRoute
import kr.yooreka.jafar.feature.setting.navigation.settingRoute

enum class TopLevelNavItem(
    val route: String,
    @StringRes val labelRes: Int,
    @DrawableRes val iconRes: Int
) {
    PROFILE(
        route = "profileRoute",
        labelRes = R.string.nav_profile,
        iconRes = R.drawable.ic_profile
    ),
    CAREER(
        route = "careerRoute",
        labelRes = R.string.nav_career,
        iconRes = R.drawable.ic_career
    ),
    PORTFOLIO(
        route = "portfolioRoute",
        labelRes = R.string.nav_portfolio,
        iconRes = R.drawable.ic_portfolio
    ),
    SETTING(
        route = "settingRoute",
        labelRes = R.string.nav_setting,
        iconRes = R.drawable.ic_setting
    )
}