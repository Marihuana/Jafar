package kr.yooreka.jafar.feature.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import kr.yooreka.jafar.R
import kr.yooreka.jafar.ui.theme.JafarTheme


@Composable
fun ProfileScreen(
    uiState: ProfileUiState = ProfileUiState()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        ProfileCard()
        Spacer(modifier = Modifier.size(16.dp))
        ContactCard()
        Spacer(modifier = Modifier.size(16.dp))
        SummaryCard()
    }
}


@Composable
fun ProfileCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .defaultMinSize(minHeight = 180.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "프로필 사진",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(80.dp),
            )
            Text(
                stringResource(R.string.profile_name)
            )
            Text(
                stringResource(R.string.profile_position)
            )
            val skills = stringArrayResource(id = R.array.profile_skill_tags)
            Row {
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
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                stringResource(R.string.contact_title),
                fontSize = 15.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(20.dp))

            ContactItem(
                iconRes = R.drawable.ic_mail,
                iconTintColor = Color.Black,
                iconBackgroundColor = Color.Magenta,
                title = stringResource(R.string.contact_mail),
                value = stringResource(R.string.contact_mail_value),
            )

            Spacer(Modifier.height(20.dp))

            ContactItem(
                iconRes = R.drawable.ic_linkedin,
                iconTintColor = Color.White,
                iconBackgroundColor = Color.Blue,
                title = stringResource(R.string.contact_linkedin),
                value = stringResource(R.string.contact_linkedin_value),
            )

            Spacer(Modifier.height(20.dp))

            ContactItem(
                iconRes = R.drawable.ic_github,
                iconTintColor = Color.White,
                iconBackgroundColor = Color.Black,
                title = stringResource(R.string.contact_github),
                value = stringResource(R.string.contact_github_value),
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
    title: String,
    value: String,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 2.dp)
    ) {
        val (iconRef, titleRef, valueRef) = createRefs()
        createVerticalChain(titleRef, valueRef, chainStyle = ChainStyle.SpreadInside)
        ContactIcon(
            iconRes = iconRes,
            iconTintColor = iconTintColor,
            iconBackgroundColor = iconBackgroundColor,
            contentDescription = title,
            modifier = Modifier.constrainAs(iconRef) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = title,
            modifier = Modifier.constrainAs(titleRef) {
                start.linkTo(iconRef.end, margin = 12.dp)
            }
        )
        Text(
            text = value,
            modifier = Modifier.constrainAs(valueRef) {
                start.linkTo(iconRef.end, margin = 12.dp)
            }
        )

    }
}

@Composable
fun SummaryCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                stringResource(R.string.summary_title),
                fontSize = 15.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.summary_introduce),
                fontSize = 19.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            Text(
                stringResource(R.string.summary_about_me),
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    ProfileCard()

}

@Preview(showBackground = true)
@Composable
fun ContactsPreview() {
    ContactCard()
}

@Preview(showBackground = true)
@Composable
fun SummaryPreview() {
    SummaryCard()
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
fun ProfileScreenPreview() {
    ProfileScreen()
}