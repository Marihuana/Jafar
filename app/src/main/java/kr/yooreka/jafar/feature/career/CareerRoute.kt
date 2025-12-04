package kr.yooreka.jafar.feature.career

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun CareerRoute(
    viewModel: CareerViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    CareerScreen(
        uiState = uiState
    )
}