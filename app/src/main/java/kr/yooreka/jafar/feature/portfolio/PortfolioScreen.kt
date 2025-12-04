package kr.yooreka.jafar.feature.portfolio

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PortfolioScreen() {
    Text(
        "저는 포트폴리오 화면입니다."
    )
}

@Preview(showBackground = true)
@Composable
fun PortfolioScreenPreview() {
    PortfolioScreen()
}
