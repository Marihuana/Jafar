package kr.yooreka.jafar.feature.portfolio

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.Cast
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.DocumentScanner
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kr.yooreka.jafar.R
import kr.yooreka.jafar.ui.theme.JafarTheme
import kr.yooreka.jafar.ui.theme.PortfolioGradientEnd
import kr.yooreka.jafar.ui.theme.PortfolioGradientStart

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PortfolioScreen(
    uiState: PortfolioUiState,
    onProjectClick: (ModuleItemUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            PortfolioHeader(
                title = stringResource(R.string.portfolio_title),
                subtitle = stringResource(R.string.portfolio_subtitle),
                modifier = Modifier.fillMaxWidth(),
            )
        }

        when {
            uiState.isLoading -> item(span = { GridItemSpan(maxLineSpan) }) {
                LoadingSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )
            }

            uiState.errorMessage != null -> item(span = { GridItemSpan(maxLineSpan) }) {
                ErrorSection(
                    message = uiState.errorMessage,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            uiState.module.list.isEmpty() -> item(span = { GridItemSpan(maxLineSpan) }) {
                EmptySection(modifier = Modifier.fillMaxWidth())
            }

            else -> {
                items(
                    items = uiState.module.list,
                    key = { it.id },
                ) { item ->
                    ProjectCard(
                        item = item,
                        onClick = { onProjectClick(item) },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    FeaturedCard(modifier = Modifier.fillMaxWidth())
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    SummaryRow(
                        summary = uiState.summary,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@Composable
private fun PortfolioHeader(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.65f),
        )
    }
}

@Composable
private fun ProjectCard(
    item: ModuleItemUiState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val installed = item.installState is InstallState.Installed
    Card(
        modifier = modifier.alpha(if (installed) 1f else 0.8f),
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.tertiary
        ),
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            val iconSize = 52.dp
            val iconShape = RoundedCornerShape(8.dp)
            val iconBackgroundColor = MaterialTheme.colorScheme.secondary

            if (item.iconUrl.isBlank()) {
                Box(
                    modifier = Modifier
                        .size(iconSize)
                        .background(iconBackgroundColor, iconShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AddCircleOutline,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = MaterialTheme.colorScheme.onSecondary,
                    )
                }
            } else {
                AsyncImage(
                    model = item.iconUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(iconSize)
                        .background(iconBackgroundColor, iconShape),
                    error = painterResource(R.drawable.img_profile),
                )
            }



            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    if (!installed) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                            modifier = Modifier.size(18.dp),
                        )
                    }
                }

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.65f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                if (installed) {
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                        modifier = Modifier.size(18.dp),
                    )
                } else {
                    AssistChip(
                        onClick = {},
                        enabled = false,
                        label = { Text(stringResource(R.string.portfolio_project_chip_coming_soon)) },
                        modifier = Modifier
                            .height(28.dp),
                    )
                }
            }
        }
    }
}

private fun ModuleItemUiState.toIcon(): ImageVector {
    return when (id) {
        "feature_map" -> Icons.Outlined.LocationOn
        "feature_streaming" -> Icons.Outlined.Cast
        "feature_ocr" -> Icons.Outlined.DocumentScanner
        else -> Icons.Outlined.ChevronRight
    }
}

@Composable
private fun FeaturedCard(
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(22.dp)

    val gradient = Brush.linearGradient(
        listOf(
            PortfolioGradientStart,
            PortfolioGradientEnd,
        )
    )

    Column(
        modifier = modifier
            .background(brush = gradient, shape = shape)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Surface(
            color = Color.White.copy(alpha = 0.18f),
            contentColor = Color.White,
            shape = RoundedCornerShape(999.dp),
        ) {
            Text(
                text = stringResource(R.string.portfolio_featured_badge),
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            )
        }

        Text(
            text = stringResource(R.string.portfolio_featured_title),
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
        )

        Text(
            text = stringResource(R.string.portfolio_featured_description),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White.copy(alpha = 0.92f),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun SummaryRow(
    summary: SummaryUiState,
    modifier: Modifier = Modifier,
) {
    val projectsValue = stringResource(
        id = R.string.portfolio_summary_plus_format,
        summary.moduleCount,
    )
    val techStackValue = stringResource(
        id = R.string.portfolio_summary_plus_format,
        summary.techStackCount,
    )

    val experienceValue = pluralStringResource(
        id = R.plurals.portfolio_summary_years,
        count = summary.experienceYears,
        summary.experienceYears,
    )

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        SummaryCard(
            valueText = projectsValue,
            label = stringResource(R.string.portfolio_summary_projects_label),
            modifier = Modifier.weight(1f),
        )
        SummaryCard(
            valueText = techStackValue,
            label = stringResource(R.string.portfolio_summary_tech_stack_label),
            modifier = Modifier.weight(1f),
        )
        SummaryCard(
            valueText = experienceValue,
            label = stringResource(R.string.portfolio_summary_experience_label),
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun SummaryCard(
    valueText: String,
    label: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.height(92.dp),
        shape = RoundedCornerShape(18.dp),
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
                .fillMaxSize()
                .padding(vertical = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = valueText,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f),
            )
        }
    }
}

@Composable
private fun LoadingSection(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun EmptySection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = stringResource(R.string.portfolio_empty_title),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = stringResource(R.string.portfolio_empty_description),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.65f),
        )
    }
}

@Composable
private fun ErrorSection(
    message: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PortfolioScreenPreview_Light() {
    JafarTheme {
        PortfolioScreen(
            uiState = portfolioUiStateMock,
            onProjectClick = {},
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PortfolioScreenPreview_Dark() {
    JafarTheme {
        PortfolioScreen(
            uiState = portfolioUiStateMock,
            onProjectClick = {},
        )
    }
}

private val portfolioUiStateMock = PortfolioUiState(
    isLoading = false,
    errorMessage = null,
    module = ModuleUiState(
        list = listOf(
            ModuleItemUiState(
                id = "feature_map",
                title = "지도",
                description = "AI 경로 추천, 오프라인 캐시",
                iconUrl = "",
                installState = InstallState.Installed,
            ),
            ModuleItemUiState(
                id = "feature_streaming",
                title = "스트리밍",
                description = "실시간 스트리밍, 채팅",
                iconUrl = "",
                installState = InstallState.Installed,
            ),
            ModuleItemUiState(
                id = "feature_ocr",
                title = "OCR",
                description = "텍스트 인식 및 추출",
                iconUrl = "",
                installState = InstallState.NotInstalled,
            ),
        )
    ),
    summary = SummaryUiState(
        moduleCount = 5,
        techStackCount = 10,
        experienceYears = 5,
    ),
)
