package kr.yooreka.jafar.feat.career

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CareerScreen(uiState: CareerUiState, onCompanyClick: (String) -> Unit) {
    LazyColumn {
        items(uiState.companies) { company ->
            CompanyCard(
                company = company,
                expanded = uiState.selectedCompanyId == company.id,
                onClick = { onCompanyClick(company.id) }
            )
        }
    }
}

@Composable
fun CompanyCard(
    company: CompanyUiState,
    expanded: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .animateContentSize()
    ) {
        Text(company.name)
        Text(company.period)
        Text(company.role)

        AnimatedVisibility(expanded) {
            Column {
                company.projects.forEach { project ->
                    ProjectCard(project)
                }
            }
        }
    }
}

@Composable
fun ProjectCard(project: ProjectUiState) {
    Column {
        Text(project.title)
        Spacer(Modifier.size(16.dp))
        Text(project.description)
        Spacer(Modifier.size(16.dp))

        AsyncImage(
            model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBfWSwdaVcnaHBS0ipb4jjho-6kUQ-OS3tZA&s",
            contentDescription = "${project.title}의 이미지",
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(start = 16.dp, end = 16.dp)
                .clip(RoundedCornerShape(16.dp)),
            placeholder = ColorPainter(Color(0xFFE0E0E0))
        )
        Spacer(Modifier.size(16.dp))
        Text("주요 성과")
        Spacer(Modifier.size(8.dp))
        Column {
            project.performance.forEach {
                Spacer(Modifier.size(4.dp))
                Text(it)
                Spacer(Modifier.size(4.dp))
            }
        }
        Spacer(Modifier.size(16.dp))
        Text("기술 스택")
        Spacer(Modifier.size(8.dp))
        Row  {
            project.skills.forEach {
                Spacer(Modifier.size(4.dp))
                Text(it)
                Spacer(Modifier.size(4.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompanyPreview() {
    CompanyUiState(
        "1",
        "11번가",
        "어제~오늘",
        "Android 개발자",
        listOf(
            ProjectUiState(
                "전사 디자인 시스템 구축",
                "회사 전체에서 사용할 수 있는 통합 디자인 시스템과 컴포넌트 라이브러리를 구축했습니다. 재사용 가능한 컴포넌트를 개발하여 개발 속도를 향상시켰습니다",
                "https://images.unsplash.com/photo-1551650975-87deedd944c3?w=800&h=600&fit=crop",
                listOf("개발 시간 40% 단축", "디자인 일관성 95% 달성"),
                listOf("kotlin", "Android")
            ),
            ProjectUiState(
                "전사 디자인 시스템 구축",
                "회사 전체에서 사용할 수 있는 통합 디자인 시스템과 컴포넌트 라이브러리를 구축했습니다. 재사용 가능한 컴포넌트를 개발하여 개발 속도를 향상시켰습니다",
                "https://images.unsplash.com/photo-1551650975-87deedd944c3?w=800&h=600&fit=crop",
                listOf("개발 시간 40% 단축", "디자인 일관성 95% 달성"),
                listOf("kotlin", "Android")
            )
        )
    ).let {
        var expanded by remember { mutableStateOf(false) }
        CompanyCard (it, expanded){
            expanded = !expanded
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProjectPreview() {
    ProjectUiState(
        "전사 디자인 시스템 구축",
        "회사 전체에서 사용할 수 있는 통합 디자인 시스템과 컴포넌트 라이브러리를 구축했습니다. 재사용 가능한 컴포넌트를 개발하여 개발 속도를 향상시켰습니다",
        "https://images.unsplash.com/photo-1551650975-87deedd944c3?w=800&h=600&fit=crop",
        listOf("개발 시간 40% 단축", "디자인 일관성 95% 달성"),
        listOf("kotlin", "Android")
    ).let {
        ProjectCard(it)
    }
}