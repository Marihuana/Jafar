package kr.yooreka.jafar.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kr.yooreka.jafar.domain.model.AppInfoVO
import kr.yooreka.jafar.domain.model.PremiumVO
import kr.yooreka.jafar.domain.model.SettingVO
import kr.yooreka.jafar.domain.repository.AppInfoRepository
import kr.yooreka.jafar.domain.repository.DisplayRepository
import kr.yooreka.jafar.domain.repository.PremiumRepository
import javax.inject.Inject

class GetSettingInfoUseCase @Inject constructor(
    private val premiumRepository: PremiumRepository,
    private val displayRepository: DisplayRepository,
    private val appInfoRepository: AppInfoRepository
) {
    operator fun invoke(): Flow<SettingVO> {
        val appInfoFlow: Flow<Result<AppInfoVO>> = flow {
            emit(appInfoRepository.getAppInfo())
        }

        return combine(
            premiumRepository.isPremium,
            displayRepository.display,
            appInfoFlow
        ) { isPremium, displayVO, appInfoResult ->
            val appInfo = appInfoResult.getOrElse {
                AppInfoVO(appVersion = "1.0.0")
            }

            val premium = PremiumVO(
                isPremium = isPremium
            )

            SettingVO(
                premium = premium,
                display = displayVO,
                appInfo = appInfo
            )
        }
    }
}