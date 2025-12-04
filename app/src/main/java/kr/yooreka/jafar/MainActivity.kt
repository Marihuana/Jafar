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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import kr.yooreka.jafar.feat.profile.ProfileScreen
import dagger.hilt.android.AndroidEntryPoint
import kr.yooreka.jafar.ui.theme.JafarTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JafarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Row(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(innerPadding)
                    ) {
                        ProfileScreen()
                    }
                }
            }
        }
    }
}





