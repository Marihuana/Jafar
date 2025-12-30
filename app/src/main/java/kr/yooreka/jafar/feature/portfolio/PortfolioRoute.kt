package kr.yooreka.jafar.feature.portfolio

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch

@Composable
fun PortfolioRoute(
    snackbarHostState: SnackbarHostState,
    viewModel: PortfolioViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.takeIf { it.isNotBlank() }?.let {
            snackbarHostState.showSnackbar(it)
        }
    }

    PortfolioScreen(
        uiState = uiState,
        onProjectClick = { item ->
            when (item.installState) {
                InstallState.Installed -> {
                    //todo 해당 모듈 띄우기
                }

                InstallState.NotInstalled -> {
                    scope.launch {
                        snackbarHostState.showSnackbar("준비중인 기능이에요")
                    }
                }
            }
        },
    )
}