package kr.yooreka.jafar.data.repository

import kr.yooreka.jafar.data.dto.ModuleDTO
import kr.yooreka.jafar.data.dto.SummaryDTO
import kr.yooreka.jafar.data.dto.toModuleVO
import kr.yooreka.jafar.data.dto.toSummaryVO
import kr.yooreka.jafar.domain.repository.PortfolioRepository
import javax.inject.Inject

class PortfolioRepositoryImpl @Inject constructor(

) : PortfolioRepository {
    override suspend fun getModules() = runCatching {
        val modules : List<ModuleDTO>? = getMockModules()  //todo API로부터 가져오기
        modules
            ?.map(ModuleDTO::toModuleVO)
            ?: emptyList()
    }

    override suspend fun getSummary() = runCatching {
        val summary : SummaryDTO? = getMockSummary()  //todo API로부터 가져오기
        requireNotNull(summary) { "Failed to get summary" }
        summary.toSummaryVO()
    }

    private fun getMockModules(): List<ModuleDTO> {
        return listOf(
            ModuleDTO(
                id = "feature_map",
                title = "지도",
                description = "AI 경로 추천, 오프라인 캐시",
                iconUrl = "",
            ),
            ModuleDTO(
                id = "feature_streaming",
                title = "스트리밍",
                description = "실시간 스트리밍, 채팅",
                iconUrl = "",
            ),
            ModuleDTO(
                id = "feature_ocr",
                title = "OCR",
                description = "텍스트 인식 및 추출",
                iconUrl = "",
            ),
        )
    }

    private fun getMockSummary(): SummaryDTO {
        return SummaryDTO(5, 5, 10)
    }
}