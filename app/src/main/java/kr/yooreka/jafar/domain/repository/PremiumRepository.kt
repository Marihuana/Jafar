package kr.yooreka.jafar.domain.repository

import kotlinx.coroutines.flow.Flow

interface PremiumRepository {
    val isPremium: Flow<Boolean>
}