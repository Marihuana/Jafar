package kr.yooreka.jafar.feature.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.yooreka.jafar.R
import kr.yooreka.jafar.domain.model.FontScale
import kr.yooreka.jafar.domain.model.Language
import kr.yooreka.jafar.ui.theme.JafarTheme

@Composable
fun SettingScreen(
    uiState: SettingUiState,
    onDarkModeToggle: (Boolean) -> Unit,
    onFontSizeClick: (FontScale) -> Unit,
    onLanguageClick: (Language) -> Unit,
    onUpgradeClick: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn(
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                SettingsHeader()
            }

            if (!uiState.premium.isPremium) {
                item {
                    PremiumCard(
                        uiState = uiState.premium,
                        onUpgradeClick = onUpgradeClick
                    )
                }
            }

            item {
                DisplayCard(
                    uiState = uiState.display,
                    onDarkModeToggle = onDarkModeToggle,
                    onFontSizeClick = onFontSizeClick,
                    onLanguageClick = onLanguageClick,
                )
            }

            item {
                AppInfoCard(
                    uiState = uiState.appVersion,
                )
            }
        }
        SettingsBottomAdBar(
            onUpgradeClick = onUpgradeClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )
    }

}

@Composable
private fun SettingsHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.setting_title),
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.setting_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}


@Composable
private fun PremiumCard(
    uiState: PremiumUiState,
    onUpgradeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 간단한 왕관 모양 대신 동그라미 아이콘 영역만 표현
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "👑",
                            fontSize = 24.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.setting_premium_title),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(R.string.setting_premium_desc),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onUpgradeClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                shape = RoundedCornerShape(999.dp)
            ) {
                Text(
                    text = stringResource(R.string.setting_premium_btn),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Composable
private fun DisplayCard(
    uiState: DisplayUiState,
    onDarkModeToggle: (Boolean) -> Unit,
    onFontSizeClick: (FontScale) -> Unit,
    onLanguageClick: (Language) -> Unit,
    modifier: Modifier = Modifier
) {
    var showFontScaleDialog by remember { mutableStateOf(false) }
    var showLanguageDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.setting_display),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Card(
            shape = RoundedCornerShape(20.dp),
        ) {
            Column {
                SettingSwitchRow(
                    title = stringResource(R.string.setting_dark_mode),
                    description = if (uiState.isDarkMode)
                        stringResource(R.string.setting_dark_mode_dark)
                    else
                        stringResource(R.string.setting_dark_mode_light),
                    checked = uiState.isDarkMode,
                    onCheckedChange = onDarkModeToggle
                )

                HorizontalDivider()

                SettingChevronRow(
                    title = stringResource(R.string.setting_font_scale),
                    description = stringResource(uiState.fontSize.toLabelResId()),
                    onClick = { showFontScaleDialog = true }
                )

                HorizontalDivider()

                SettingChevronRow(
                    title = stringResource(R.string.setting_language),
                    description = stringResource(uiState.language.toLabelResId()),
                    onClick = { showLanguageDialog = true }
                )
            }
        }

        if (showFontScaleDialog) {
            FontScaleDialog(
                current = uiState.fontSize,
                onSelect = { selected ->
                    onFontSizeClick(selected)
                    showFontScaleDialog = false
                },
                onDismiss = { showFontScaleDialog = false }
            )
        }

        if (showLanguageDialog) {
            LanguageDialog(
                current = uiState.language,
                onSelect = { selected ->
                    onLanguageClick(selected)
                    showLanguageDialog = false
                },
                onDismiss = { showLanguageDialog = false }
            )
        }
    }
}

@Composable
private fun SettingSwitchRow(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
private fun SettingChevronRow(
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Icon(
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
            contentDescription = null
        )
    }
}

@Composable
private fun AppInfoCard(
    uiState: AppInfoUiState,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.setting_version) + uiState.appVersion,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.setting_copy_right),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
private fun SettingsBottomAdBar(
    onUpgradeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.tertiary)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "광고",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "프리미엄으로 광고 없이 사용하기",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Button(
            onClick = onUpgradeClick,
            shape = RoundedCornerShape(999.dp)
        ) {
            Text(text = "업그레이드")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PremiumCardPreview() {
    JafarTheme {
        PremiumCard(
            uiState = PremiumUiState(),
            onUpgradeClick = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DisplayCardPreview() {
    JafarTheme {
        DisplayCard(
            uiState = DisplayUiState(),
            onDarkModeToggle = {},
            onFontSizeClick = {},
            onLanguageClick = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun AppInfoCardPreview() {
    JafarTheme {
        AppInfoCard(uiState = AppInfoUiState())
    }
}

@Composable
@Preview(showBackground = true)
fun SettingScreenLightModePreview() {
    val uiState = SettingUiState()
    JafarTheme(
        darkTheme = false
    ) {
        SettingScreen(
            uiState = uiState,
            onDarkModeToggle = {},
            onFontSizeClick = {},
            onLanguageClick = {},
            onUpgradeClick = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SettingScreenDarkModePreview() {
    val uiState = SettingUiState()
    JafarTheme(
        darkTheme = true
    ) {
        SettingScreen(
            uiState = uiState,
            onDarkModeToggle = {},
            onFontSizeClick = {},
            onLanguageClick = {},
            onUpgradeClick = {}
        )
    }
}
