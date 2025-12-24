package kr.yooreka.jafar.feature.career

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kr.yooreka.jafar.R
import kr.yooreka.jafar.ui.theme.JafarTheme

@Composable
fun CareerScreen(
    uiState: CareerUiState,
    modifier: Modifier = Modifier,
    onCompanyClick: (Long) -> Unit,
) {
    val companies = uiState.companies
    val selectedCompanyId = rememberSaveable { mutableStateListOf(companies.firstOrNull() ?: 0L) }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        items(companies) { company ->
            CompanyCard(
                company = company,
                expanded = selectedCompanyId.contains(company.id),
                onClick = {
                    if (selectedCompanyId.contains(company.id)) {
                        selectedCompanyId.remove(company.id)
                    } else
                        selectedCompanyId.add(company.id)
                }
            )
        }
    }
}

@Composable
fun CompanyCard(
    company: CompanyUiState,
    expanded: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline
        ),
        shape = RoundedCornerShape(16.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessHigh
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_company),
                    contentDescription = stringResource(R.string.career_company_icon_cd),
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.tertiary)
                        .padding(8.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                )
                Spacer(Modifier.size(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        company.name,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Spacer(Modifier.size(8.dp))
                    Text(
                        company.role,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(Modifier.size(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            imageVector = Icons.Rounded.DateRange,
                            contentDescription = stringResource(R.string.career_period_icon_cd),
                            modifier = Modifier.size(16.dp),
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            company.period,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                Image(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = if (expanded) stringResource(R.string.career_collapse_project_cd) else stringResource(
                        R.string.career_expand_project_cd
                    ),
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
                )
            }

            AnimatedVisibility(expanded) {
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    Spacer(Modifier.size(16.dp))
                    Text(
                        text = pluralStringResource(
                            id = R.plurals.career_projects_count,
                            count = company.projects.size,
                            company.projects.size
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(Modifier.size(12.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        company.projects.forEach { project ->
                            ProjectCard(project)
                        }
                    }
                    Spacer(Modifier.size(16.dp))
                }
            }
        }
    }
}

@Composable
fun ProjectCard(
    project: ProjectUiState,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline
        ),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = project.title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.size(16.dp))
            Text(
                text = project.description,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.size(8.dp))

            AsyncImage(
                model = project.imageUrl,
                contentDescription = stringResource(
                    R.string.career_project_image_cd,
                    project.title
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(16.dp)),
                placeholder = ColorPainter(MaterialTheme.colorScheme.tertiary)
            )
            Spacer(Modifier.size(16.dp))
            Text(
                text = stringResource(R.string.career_performance),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(Modifier.size(8.dp))
            project.performance.forEach {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                    Spacer(Modifier.size(8.dp))
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            Spacer(Modifier.size(16.dp))
            Text(
                text = stringResource(R.string.career_tech_stack),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(Modifier.size(8.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                project.skills.forEach { tag ->
                    TechTag(tag)
                }
            }
        }
    }


}

@Composable
fun TechTag(
    name: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = name,
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.bodySmall
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CompanyPreview() {
    JafarTheme {
        var expanded by remember { mutableStateOf(false) }
        CompanyCard(getCompanyUiStateMock(), expanded) {
            expanded = !expanded
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ExpandedCompanyPreview() {
    JafarTheme {
        var expanded by remember { mutableStateOf(true) }
        CompanyCard(getCompanyUiStateMock(), expanded) {
            expanded = !expanded
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProjectPreview() {
    JafarTheme {
        ProjectUiState(
            1L,
            "전사 디자인 시스템 구축",
            "회사 전체에서 사용할 수 있는 통합 디자인 시스템과 컴포넌트 라이브러리를 구축했습니다. 재사용 가능한 컴포넌트를 개발하여 개발 속도를 향상시켰습니다",
            "https://images.unsplash.com/photo-1551650975-87deedd944c3?w=800&h=600&fit=crop",
            listOf("개발 시간 40% 단축", "디자인 일관성 95% 달성"),
            listOf("kotlin", "Android")
        ).let {
            ProjectCard(it)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CareerPreview() {
    JafarTheme {
        CareerScreen()
    }
}


private fun getCompanyUiStateMock() = CompanyUiState(
    1,
    "11번가",
    "2024.11.20 ~ 현재",
    "안드로이드 개발자",
    listOf(
        ProjectUiState(
            1L,
            "전사 디자인 시스템 구축",
            "회사 전체에서 사용할 수 있는 통합 디자인 시스템과 컴포넌트 라이브러리를 구축했습니다. 재사용 가능한 컴포넌트를 개발하여 개발 속도를 향상시켰습니다",
            "https://images.unsplash.com/photo-1551650975-87deedd944c3?w=800&h=600&fit=crop",
            listOf("개발 시간 40% 단축", "디자인 일관성 95% 달성"),
            listOf("kotlin", "Android")
        ),
        ProjectUiState(
            1L,
            "전사 디자인 시스템 구축",
            "회사 전체에서 사용할 수 있는 통합 디자인 시스템과 컴포넌트 라이브러리를 구축했습니다. 재사용 가능한 컴포넌트를 개발하여 개발 속도를 향상시켰습니다",
            "https://images.unsplash.com/photo-1551650975-87deedd944c3?w=800&h=600&fit=crop",
            listOf("개발 시간 40% 단축", "디자인 일관성 95% 달성"),
            listOf("kotlin", "Android")
        )
    )
)

@Preview(showBackground = true)
@Composable
fun CareerScreenPreview() {
    CareerScreen()
}
