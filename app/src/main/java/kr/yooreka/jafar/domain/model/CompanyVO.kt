package kr.yooreka.jafar.domain.model

data class CompanyVO (
    val id: Long,
    val name: String,
    val started: Long,
    val ended: Long,
    val role: String,
    val projects: List<ProjectVO>
)

data class ProjectVO (
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val performance: List<String>,
    val skills: List<String>
)