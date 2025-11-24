package kr.yooreka.jafar.feat.career

data class CareerUiState(
    val companies: List<CompanyUiState>,
    val selectedCompanyId: String? = null
)

data class CompanyUiState(
    val id: String,
    val name: String,
    val period: String,
    val role: String,
    val projects: List<ProjectUiState>
)

data class ProjectUiState(
    val title: String,
    val description: String,
    val imageUrl: String,
    val performance: List<String>,
    val skills: List<String>
)