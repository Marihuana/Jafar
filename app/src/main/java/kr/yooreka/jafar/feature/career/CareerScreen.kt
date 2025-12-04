package kr.yooreka.jafar.feature.career

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CareerScreen() {
    Text(
        "저는 경력 화면입니다."
    )
}

@Preview(showBackground = true)
@Composable
fun CareerScreenPreview() {
    CareerScreen()
}
