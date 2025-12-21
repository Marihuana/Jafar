package kr.yooreka.jafar

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.yooreka.jafar.domain.model.FontScale
import kr.yooreka.jafar.domain.model.Language
import kr.yooreka.jafar.navigation.JafarNavHost
import kr.yooreka.jafar.navigation.TopLevelNavItem
import kr.yooreka.jafar.ui.theme.JafarTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val display by viewModel.displayState.collectAsStateWithLifecycle()

            val isDarkTheme = display.isDarkMode

            val fontScaleFactor = when (display.fontScale) {
                FontScale.SMALL -> 0.85f
                FontScale.LARGE -> 1.15f
                else -> 1f
            }

            LaunchedEffect(display.language) {
                val appLocales = when (display.language) {
                    Language.KOREAN -> LocaleListCompat.forLanguageTags("ko")
                    Language.ENGLISH -> LocaleListCompat.forLanguageTags("en")
                    Language.JAPANESE -> LocaleListCompat.forLanguageTags("ja")
                }
                AppCompatDelegate.setApplicationLocales(appLocales)
            }

            JafarTheme(
                darkTheme = isDarkTheme,
                fontScaleFactor = fontScaleFactor
            ) {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                val windowSizeClass = calculateWindowSizeClass(this)
                val widthSizeClass = windowSizeClass.widthSizeClass

                JafaScreen(
                    navController = navController,
                    currentDestination = currentDestination,
                    widthSizeClass = widthSizeClass
                )
            }
        }
    }
}

@Composable
fun JafaScreen(
    navController: NavHostController,
    currentDestination: NavDestination?,
    widthSizeClass: WindowWidthSizeClass
) {
    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    JafarBottomBar(
                        currentDestination = currentDestination,
                        onNavigateToTopLevelDestination = { destination ->
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            ) { innerPadding ->
                JafarNavHost(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        WindowWidthSizeClass.Medium,
        WindowWidthSizeClass.Expanded -> {
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                Row(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    JafarNavigationRail(
                        currentDestination = currentDestination,
                        onNavigateToTopLevelDestination = { destination ->
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                    JafarNavHost(
                        navController = navController,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }


}

@Composable
fun JafarBottomBar(
    currentDestination: NavDestination?,
    onNavigateToTopLevelDestination: (TopLevelNavItem) -> Unit
) {
    NavigationBar {
        TopLevelNavItem.entries.forEach { destination ->
            val selected = currentDestination
                ?.hierarchy
                ?.any { it.route == destination.route } == true

            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToTopLevelDestination(destination) },
                icon = { NavIcon(selected, navItem = destination) },
                label = { Text(text = stringResource(destination.labelRes)) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.secondary,
                    unselectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.onSecondary,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    indicatorColor = MaterialTheme.colorScheme.onSecondary
                )

            )
        }
    }
}

@Composable
fun JafarNavigationRail(
    currentDestination: NavDestination?,
    onNavigateToTopLevelDestination: (TopLevelNavItem) -> Unit
) {
    NavigationRail {
        TopLevelNavItem.entries.forEach { destination ->
            val selected = currentDestination
                ?.hierarchy
                ?.any { it.route == destination.route } == true

            NavigationRailItem(
                selected = selected,
                onClick = { onNavigateToTopLevelDestination(destination) },
                icon = { NavIcon(selected, destination) },
            )
        }
    }
}

@Composable
fun NavIcon(
    isSelected: Boolean = false,
    navItem: TopLevelNavItem
) {
    Icon(
        painter = painterResource(navItem.iconRes),
        contentDescription = stringResource(navItem.labelRes),
        modifier = Modifier.size(24.dp),
        tint = if (isSelected) {
            MaterialTheme.colorScheme.secondary
        } else {
            MaterialTheme.colorScheme.onPrimary
        }
    )
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(
    name = "Jafar Compact",
    showBackground = true,
    widthDp = 360,
    heightDp = 640
)
@Composable
fun JafarCompactPreview() {
    JafarTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        val windowSizeClass = WindowSizeClass.calculateFromSize(
            DpSize(width = 360.dp, height = 640.dp)
        )
        val widthSizeClass = windowSizeClass.widthSizeClass

        JafaScreen(
            navController = navController,
            currentDestination = currentDestination,
            widthSizeClass = widthSizeClass
        )
    }
}


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(
    name = "Jafar Expanded",
    showBackground = true,
    widthDp = 1024,
    heightDp = 640
)
@Composable
fun JafarExpandedPreview() {
    JafarTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        val windowSizeClass = WindowSizeClass.calculateFromSize(
            DpSize(width = 1024.dp, height = 640.dp)
        )
        val widthSizeClass = windowSizeClass.widthSizeClass

        JafaScreen(
            navController = navController,
            currentDestination = currentDestination,
            widthSizeClass = widthSizeClass
        )
    }
}

