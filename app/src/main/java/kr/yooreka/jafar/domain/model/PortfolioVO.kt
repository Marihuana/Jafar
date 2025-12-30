package kr.yooreka.jafar.domain.model

data class ModuleVO(
    val id: String,
    val title: String,
    val description: String,
    val iconUrl: String
)

data class SummaryVO(
    val moduleCount: Int,
    val techStackCount: Int,
    val experienceYears: Int
)