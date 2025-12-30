package kr.yooreka.jafar.data.repository

import kr.yooreka.jafar.domain.model.AppInfoVO
import kr.yooreka.jafar.domain.repository.AppInfoRepository
import javax.inject.Inject

class AppInfoRepositoryImpl @Inject constructor(
) : AppInfoRepository {
    override suspend fun getAppInfo(): Result<AppInfoVO> {
        val appVersion = getAppVersion()
        return Result.success(AppInfoVO(appVersion))
    }

    private fun getAppVersion(): String {
        return "1.0.0"
    }
}