package kr.yooreka.jafar.feature.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kr.yooreka.jafar.R
import kr.yooreka.jafar.domain.model.FontScale
import kr.yooreka.jafar.domain.model.Language
import kr.yooreka.jafar.ui.theme.JafarTheme

@Composable
private fun SelectableOptionRow(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    leading: (@Composable () -> Unit)? = null,
    onClick: () -> Unit
) {
    val borderColor = if (selected) {
        MaterialTheme.colorScheme.onSecondary
    } else {
        MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.3f)
    }

    val backgroundColor = if (selected) {
        MaterialTheme.colorScheme.secondary
    } else {
        MaterialTheme.colorScheme.surface
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leading != null) {
            leading()
            Spacer(modifier = Modifier.width(12.dp))
        }

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )

        if (selected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}

@Composable
fun FontScaleDialog(
    current: FontScale,
    onSelect: (FontScale) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            tonalElevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = stringResource(R.string.setting_font_scale_dialog_title),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = stringResource(R.string.setting_font_scale_dialog_subtitle),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(onClick = onDismiss),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // 옵션 리스트
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    FontScale.entries.forEach { scale ->
                        SelectableOptionRow(
                            text = stringResource(id = scale.toLabelResId()),
                            selected = scale == current,
                        ) {
                            onSelect(scale)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LanguageDialog(
    current: Language,
    onSelect: (Language) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            tonalElevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 20.dp)
            ) {
                // 헤더
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = stringResource(R.string.setting_language_dialog_title),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = stringResource(R.string.setting_language_dialog_subtitle),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(onClick = onDismiss),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Language.entries.forEach { language ->
                        SelectableOptionRow(
                            text = stringResource(id = language.toLabelResId()),
                            selected = language == current,
                            leading = {
                                Text(
                                    text = language.flagEmoji(),
                                    fontSize = 22.sp
                                )
                            }
                        ) {
                            onSelect(language)
                        }
                    }
                }
            }
        }
    }
}

private fun Language.flagEmoji(): String = when (this) {
    Language.KOREAN -> "🇰🇷"
    Language.ENGLISH -> "🇺🇸"
    Language.JAPANESE -> "🇯🇵"
}

@Composable
@Preview(showBackground = true)
fun SelectedOptionRowPreview() {
    JafarTheme {
        SelectableOptionRow(
            "가나다라마바사",
            selected = true,
            onClick = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun UnSelectedOptionRowPreview() {
    JafarTheme {
        SelectableOptionRow(
            "가나다라마바사",
            selected = false,
            onClick = {}
        )
    }
}


@Composable
@Preview(showBackground = true)
fun FontScaleDialogLightModePreview() {
    JafarTheme {
        FontScaleDialog(
            current = FontScale.NORMAL,
            onSelect = {},
            onDismiss = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun FontScaleDialogDarkModePreview() {
    JafarTheme(
        darkTheme = true
    ) {
        FontScaleDialog(
            current = FontScale.NORMAL,
            onSelect = {},
            onDismiss = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun LanguageDialogPreview() {
    JafarTheme {
        LanguageDialog(
            current = Language.KOREAN,
            onSelect = {},
            onDismiss = {}
        )
    }
}
