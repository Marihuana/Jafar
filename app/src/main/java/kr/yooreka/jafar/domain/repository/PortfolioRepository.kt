package kr.yooreka.jafar.domain.repository

import kr.yooreka.jafar.domain.model.ModuleVO
import kr.yooreka.jafar.domain.model.SummaryVO

interface PortfolioRepository {
    suspend fun getModules(): Result<List<ModuleVO>>
    suspend fun getSummary(): Result<SummaryVO>
}
