package kr.yooreka.jafar.feature.career

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CareerRoute(
    viewModel: CareerViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CareerScreen(
        uiState = uiState,
        onCompanyClick = viewModel::toggleCompanyExpanded
    )
}