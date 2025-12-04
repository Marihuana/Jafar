package kr.yooreka.jafar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.yooreka.jafar.feature.profile.ProfileScreen
import kr.yooreka.jafar.navigation.JafarNavHost
import kr.yooreka.jafar.navigation.TopLevelNavItem
import kr.yooreka.jafar.ui.theme.JafarTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JafarTheme {
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
){
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
                label = { Text(text = stringResource(destination.labelRes)) }
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
){
    Icon(
        painter = painterResource(navItem.iconRes),
        contentDescription = stringResource(navItem.labelRes),
        modifier = Modifier.size(24.dp),
        tint = if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
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

