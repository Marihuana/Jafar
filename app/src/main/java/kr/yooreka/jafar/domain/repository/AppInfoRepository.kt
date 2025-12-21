package kr.yooreka.jafar.domain.repository

import kr.yooreka.jafar.domain.model.AppInfoVO

interface AppInfoRepository {
    suspend fun getAppInfo(): Result<AppInfoVO>
}