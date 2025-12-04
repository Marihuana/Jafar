package kr.yooreka.jafar.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.yooreka.jafar.domain.model.CompanyVO

interface CareerRepository {
    val companies : Flow<List<CompanyVO>>
}