package kr.yooreka.jafar.feature.career

import kr.yooreka.jafar.domain.model.CompanyVO
import kr.yooreka.jafar.domain.model.ProjectVO
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.max

data class CareerUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val companies: List<CompanyUiState> = emptyList(),
    val selectedCompanyId: String? = null
)

data class CompanyUiState(
    val id: Long,
    val name: String,
    val period: String,
    val role: String,
    val projects: List<ProjectUiState>
)

fun CompanyVO.toUiState(): CompanyUiState = CompanyUiState(
    id = id,
    name = name,
    period = started toPeriodText ended,
    role = role,
    projects = projects.map(ProjectVO::toUIState)
)

private val EMPLOYMENT_DATE_FORMATTER: DateTimeFormatter =
    DateTimeFormatter.ofPattern("yyyy.MM.dd")

private infix fun Long.toPeriodText(endMillis: Long): String {
    val zoneId = ZoneId.systemDefault()

    val startDate: LocalDate = Instant.ofEpochMilli(this)
        .atZone(zoneId)
        .toLocalDate()

    val endDate: LocalDate = Instant.ofEpochMilli(endMillis)
        .atZone(zoneId)
        .toLocalDate()

    // 입사일보다 퇴사일이 빠른 케이스는 바로 잡아주거나 예외를 던질 수 있음
    require(!endDate.isBefore(startDate)) {
        "퇴사일($endDate)이 입사일($startDate)보다 이전일 수 없습니다."
    }

    val period: Period = Period.between(startDate, endDate)
    val years = period.years
    val months = period.months
    val days = period.days

    val durationParts = mutableListOf<String>()
    if (years > 0) durationParts += "${years}년"
    if (months > 0) durationParts += "${months}개월"

    if (durationParts.isEmpty()) {
        durationParts += "${max(days, 1)}일"
    }

    val durationText = durationParts.joinToString(" ")

    val startText = startDate.format(EMPLOYMENT_DATE_FORMATTER)
    val endText = endDate.format(EMPLOYMENT_DATE_FORMATTER)

    return "$startText ~ $endText ($durationText)"
}

data class ProjectUiState(
    val id: Long,
    val title: String,
    val description: String,
    val imageUrl: String,
    val performance: List<String>,
    val skills: List<String>
)

fun ProjectVO.toUIState() : ProjectUiState =
    ProjectUiState(
        id = id,
        title = name,
        description = description,
        imageUrl = imageUrl,
        performance = performance,
        skills = skills
    )