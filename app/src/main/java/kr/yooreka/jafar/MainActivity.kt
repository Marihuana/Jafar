package kr.yooreka.jafar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.yooreka.jafar.ui.theme.JafarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JafarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Row(modifier = Modifier.padding(innerPadding)) {
                        ProfileCard()
                    }
                }
            }
        }
    }
}


@Composable
fun ProfileCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "프로필 사진",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(80.dp),
            )
            Text(
                stringResource(R.string.name)
            )
            Text(
                stringResource(R.string.job)
            )
            val skills = stringArrayResource(id = R.array.skills)
            Row(
            ) {
                skills.forEach { item ->
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = item,
                        Modifier
                            .background(Color.LightGray, RoundedCornerShape(4.dp))
                            .padding(start = 8.dp, end = 8.dp)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                }
            }
        }
    }
}

@Composable
fun ContactCard() {

}

@Composable
fun SummaryCard() {

}

@Composable
fun Home() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ProfileCard()
        ContactCard()
        SummaryCard()
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    JafarTheme {
        ProfileCard()
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    JafarTheme {
        Home()
    }
}