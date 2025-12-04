package kr.yooreka.jafar.feature.setting

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingScreen(){
    Text(
        "저는..알지?"
    )
}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    SettingScreen()
}
