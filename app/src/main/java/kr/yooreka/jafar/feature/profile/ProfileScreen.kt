package kr.yooreka.jafar.feature.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProfileScreen(
    uiState: ProfileUiState = ProfileUiState()
) {
    Text(
        "저는 프로필 화면입니다."
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}