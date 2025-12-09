package kr.yooreka.jafar.feature.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kr.yooreka.jafar.R
import kr.yooreka.jafar.ui.theme.JafarTheme


@Composable
fun ProfileScreen(
    uiState: ProfileUiState = ProfileUiState(isLoading = true),
    onMailClicked: () -> Unit = {},
    onLinkedinClicked: () -> Unit = {},
    onGithubClicked: () -> Unit = {}
) {
    val aboutMe = uiState.aboutMe
    val contact = uiState.contact
    val introduce = uiState.introduce

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        ProfileCard(state = aboutMe ?: AboutMeUIState())
        Spacer(modifier = Modifier.size(16.dp))
        ContactCard(
            state = contact ?: ContactUIState(),
            onMailClicked = onMailClicked,
            onLinkedinClicked = onLinkedinClicked,
            onGithubClicked = onGithubClicked
        )
        Spacer(modifier = Modifier.size(16.dp))
        IntroduceCard(state = introduce ?: IntroduceUIState())
    }
}


@Composable
fun ProfileCard(
    state: AboutMeUIState = AboutMeUIState()
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.tertiary
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .defaultMinSize(minHeight = 180.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = state.imgUrl,
                contentDescription = stringResource(R.string.profile_img_description),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(80.dp),
                placeholder = painterResource(R.drawable.img_profile)
            )
            Text(
                text = state.name,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = state.position,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 15.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Medium
            )

            Row {
                state.skills.forEach { item ->
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = item,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.secondary,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(start = 8.dp, end = 8.dp)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                }
            }
        }
    }
}

@Composable
fun ContactCard(
    state: ContactUIState = ContactUIState(),
    onMailClicked: () -> Unit = {},
    onLinkedinClicked: () -> Unit = {},
    onGithubClicked: () -> Unit = {}
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.tertiary
        ),
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                stringResource(R.string.contact_title),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 15.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(20.dp))

            ContactItem(
                iconRes = R.drawable.ic_mail,
                iconTintColor = Color(0xFF1A65FC),
                iconBackgroundColor = Color(0xFFDAEBFF),
                label = stringResource(R.string.contact_mail),
                value = state.mail,
                onItemClicked = onMailClicked
            )

            Spacer(Modifier.height(20.dp))

            ContactItem(
                iconRes = R.drawable.ic_github,
                iconTintColor = Color.White,
                iconBackgroundColor = Color.Black,
                label = stringResource(R.string.contact_github),
                value = state.github,
                onItemClicked = onGithubClicked
            )

            Spacer(Modifier.height(20.dp))

            ContactItem(
                iconRes = R.drawable.ic_linkedin,
                iconTintColor = Color.White,
                iconBackgroundColor = Color.Blue,
                label = stringResource(R.string.contact_linkedin),
                value = state.linkedin,
                onItemClicked = onLinkedinClicked
            )
        }
    }
}

@Composable
fun ContactIcon(
    @DrawableRes iconRes: Int,
    iconTintColor: Color,
    iconBackgroundColor: Color,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Icon(
        painter = painterResource(iconRes),
        contentDescription = contentDescription,
        tint = iconTintColor,
        modifier = modifier
            .size(40.dp)
            .background(iconBackgroundColor, CircleShape)
            .padding(10.dp)
    )
}

@Composable
fun ContactItem(
    @DrawableRes iconRes: Int,
    iconTintColor: Color,
    iconBackgroundColor: Color,
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    onItemClicked: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClicked),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(iconBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            ContactIcon(
                iconRes = iconRes,
                iconTintColor = iconTintColor,
                iconBackgroundColor = iconBackgroundColor,
                contentDescription = label,
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = label,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = value,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun IntroduceCard(
    state: IntroduceUIState = IntroduceUIState()
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.tertiary
        ),
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = stringResource(R.string.introduce_title),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 15.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = state.description,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 14.sp,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    JafarTheme() {
        ProfileCard(
            state = AboutMeUIState(
                "홍길동",
                null,
                "안드로이드",
                listOf("java", "kotlin", "Node.js")
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContactsPreview() {
    JafarTheme {
        ContactCard(
            state = ContactUIState(
                "sdlfkjsdflkj",
                "sdlkfjsdsdf;lksd;lfksdlfjlksdjflkjsdfjlksdfjklkfjlksdfjlksdf",
                "lsdkfjlksdfjlksdfjlk"
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IntroducePreview() {
    JafarTheme {
        IntroduceCard(
            state = IntroduceUIState("동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라만세. 무궁화 삼천리 화려강산 대한사람 대한으로 길이 보전하세")
        )
    }

}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    JafarTheme {
        ProfileScreen()
    }
}


@Preview(showBackground = true)
@Composable
fun HomePreviewDark() {
    JafarTheme(darkTheme = true) {
        ProfileScreen()
    }
}
