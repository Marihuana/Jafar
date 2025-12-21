package kr.yooreka.jafar.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.yooreka.jafar.domain.repository.PremiumRepository
import javax.inject.Inject

class PremiumRepositoryImpl @Inject constructor(
) : PremiumRepository {
    override val isPremium: Flow<Boolean> = flow {
        emit(isPremium())
    }

    private suspend fun isPremium(): Boolean {
        return false
    }
}