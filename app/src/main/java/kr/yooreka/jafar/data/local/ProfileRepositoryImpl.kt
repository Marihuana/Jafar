package kr.yooreka.jafar.data.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.yooreka.jafar.data.dto.ProfileDTO
import kr.yooreka.jafar.domain.repository.ProfileRepository
import kr.yooreka.jafar.domain.model.ProfileVO
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor() : ProfileRepository {

    override suspend fun getProfile(): Result<ProfileVO> {
        return Result.success(
            getMockProfile().toProfileVO()
        )
    }

    private fun getMockProfile() = ProfileDTO(
        name = "유재홍",
        imgUrl = "https://media.licdn.com/dms/image/v2/D5603AQFb425WKJ2bLA/profile-displayphoto-crop_800_800/B56Zr.Zg4ULAAI-/0/1765204693408?e=1768435200&v=beta&t=LCIt5N6WhtOigEDE1NzhvUREQDr8cSn28nKCuBoz5ak",
        position = "Android Developer",
        skills = listOf("Java", "Kotlin", "Android"),
        description = "10년간 안드로이드 개발자로서 스타트업부터 대기업, 신규 프로젝트 런칭부터 MAU 900만 이상의 대형 서비스 운영까지 다양한 환경에서, 폭 넓은 경험을 쌓았습니다.\n" +
                "협업 과정에서 상대방을 배려하며 팀의 성과를 극대화하는 것을 중요시하며, 코드 품질 개선과최신 기술 도입을 통해 지속적으로 성장하고 있습니다.\n" +
                "사용자에게 가치를 전달하는 개발자로서 앞으로도 도전과 배움을 이어가고자 합니다.",
        mail = "bracket0723@gmail.com",
        linkedin = "https://www.linkedin.com/in/jacob-yoo-a3593a21b",
        github = "https://github.com/Marihuana",
    )
}