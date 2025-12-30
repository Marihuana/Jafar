package kr.yooreka.jafar.feature.portfolio

import kr.yooreka.jafar.domain.model.ModuleVO
import kr.yooreka.jafar.domain.model.SummaryVO

data class PortfolioUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val module: ModuleUiState = ModuleUiState(),
    val summary: SummaryUiState = SummaryUiState(
        moduleCount = 0,
        techStackCount = 0,
        experienceYears = 0
    ),
)

data class ModuleUiState(
    val list: List<ModuleItemUiState> = emptyList(),
)

data class ModuleItemUiState(
    val id: String, // SplitInstall moduleName과 1:1로 매핑되는 다이나믹 모듈 키 (예: "feature_map")
    val title: String,
    val description: String,
    val iconUrl: String,
    val installState: InstallState = InstallState.NotInstalled,
)

sealed interface InstallState {
    data object Installed : InstallState
    data object NotInstalled : InstallState
}

data class SummaryUiState(
    val moduleCount: Int,
    val techStackCount: Int,
    val experienceYears: Int,
)

fun List<ModuleVO>.toUiState(
    installStateProvider: (String) -> InstallState
) = ModuleUiState(
    list = this.map {
        ModuleItemUiState(
            id = it.id,
            title = it.title,
            description = it.description,
            iconUrl = it.iconUrl,
            installState = installStateProvider.invoke(it.id),
        )
    }
)

fun SummaryVO.toUiState() = SummaryUiState(
    moduleCount = this.moduleCount,
    techStackCount = this.techStackCount,
    experienceYears = this.experienceYears
)