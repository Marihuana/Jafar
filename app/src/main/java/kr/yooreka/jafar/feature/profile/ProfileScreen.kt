package kr.yooreka.jafar.feature.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kr.yooreka.jafar.R
import kr.yooreka.jafar.ui.theme.GithubIconBackground
import kr.yooreka.jafar.ui.theme.GithubIconTint
import kr.yooreka.jafar.ui.theme.JafarTheme
import kr.yooreka.jafar.ui.theme.LinkedinIconBackground
import kr.yooreka.jafar.ui.theme.LinkedinIconTint
import kr.yooreka.jafar.ui.theme.MailIconBackground
import kr.yooreka.jafar.ui.theme.MailIconTint


@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    onMailClicked: () -> Unit,
    onLinkedinClicked: () -> Unit,
    onGithubClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val aboutMe = uiState.aboutMe
    val contact = uiState.contact
    val introduce = uiState.introduce

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
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
    state: AboutMeUIState,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
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
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            Text(
                text = state.position,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
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
    state: ContactUIState,
    onMailClicked: () -> Unit,
    onLinkedinClicked: () -> Unit,
    onGithubClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
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
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(Modifier.height(20.dp))

            ContactItem(
                iconRes = R.drawable.ic_mail,
                iconTintColor = MailIconTint,
                iconBackgroundColor = MailIconBackground,
                label = stringResource(R.string.contact_mail),
                value = state.mail,
                onItemClicked = onMailClicked
            )

            Spacer(Modifier.height(20.dp))

            ContactItem(
                iconRes = R.drawable.ic_github,
                iconTintColor = GithubIconTint,
                iconBackgroundColor = GithubIconBackground,
                label = stringResource(R.string.contact_github),
                value = state.github,
                onItemClicked = onGithubClicked
            )

            Spacer(Modifier.height(20.dp))

            ContactItem(
                iconRes = R.drawable.ic_linkedin,
                iconTintColor = LinkedinIconTint,
                iconBackgroundColor = LinkedinIconBackground,
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
    onItemClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClicked),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ContactIcon(
            iconRes = iconRes,
            iconTintColor = iconTintColor,
            iconBackgroundColor = iconBackgroundColor,
            contentDescription = label,
        )

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
    state: IntroduceUIState,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
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
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = state.description,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    JafarTheme {
        ProfileCard(
            state = mockProfileUiState.aboutMe!!
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContactsPreview() {
    JafarTheme {
        ContactCard(
            state = mockProfileUiState.contact!!,
            onMailClicked = {},
            onLinkedinClicked = {},
            onGithubClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IntroducePreview() {
    JafarTheme {
        IntroduceCard(
            state = mockProfileUiState.introduce!!
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    JafarTheme {
        ProfileScreen(
            uiState = mockProfileUiState,
            onMailClicked = {},
            onLinkedinClicked = {},
            onGithubClicked = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreviewDark() {
    JafarTheme(darkTheme = true) {
        ProfileScreen(
            uiState = mockProfileUiState,
            onMailClicked = {},
            onLinkedinClicked = {},
            onGithubClicked = {}
        )
    }
}

private val mockProfileUiState = ProfileUiState(
    aboutMe = AboutMeUIState(
        "홍길동",
        null,
        "안드로이드",
        listOf("java", "kotlin", "Node.js")
    ),
    contact = ContactUIState(
        "abc@def.com",
        "https://www.linkedin.com/in/jacob-yoo-a3593a21b/",
        "https://github.com/jacob-yoo"
    ),
    introduce = IntroduceUIState(
        "인사 하네요 근심없게 나 아름다운 방식으로 무딘 목소리와 어설픈 자국들 화려하게 장식해줘요 그대 춤을 추는 나무 같아요 그 안에 투박한 음악은 나예요 네 곁에만 움츠린 두려움들도 애틋한 그림이 되겠죠 그럼 돼요\n" +
                "웃어 줄래요 사진처럼 수줍은 맘이 다 녹아내리게 무력한 걸음과 혼잡한 TV 속 세상없이 또 울기도 해요 그대 춤을 추는 나무 같아요 그 안에 투박한 음악은 나예요 네 곁에만 움츠린 두려움들도 애틋한 그림이 되겠죠 그럼 돼요"
    )
)