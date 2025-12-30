package kr.yooreka.jafar.data.dto

import kr.yooreka.jafar.domain.model.ModuleVO
import kr.yooreka.jafar.domain.model.SummaryVO

data class ModuleDTO(
    val id: String,
    val title: String,
    val description: String,
    val iconUrl: String
)

data class SummaryDTO(
    val moduleCount: Int,
    val techStackCount: Int,
    val experienceYears: Int
)

fun ModuleDTO.toModuleVO() = ModuleVO(
    id = id,
    title = title,
    description = description,
    iconUrl = iconUrl
)

fun SummaryDTO.toSummaryVO() = SummaryVO(
    moduleCount = moduleCount,
    techStackCount = techStackCount,
    experienceYears = experienceYears
)


