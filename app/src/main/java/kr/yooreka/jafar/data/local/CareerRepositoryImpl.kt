package kr.yooreka.jafar.data.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.yooreka.jafar.domain.model.CompanyVO
import kr.yooreka.jafar.domain.model.ProjectVO
import kr.yooreka.jafar.domain.repository.CareerRepository
import javax.inject.Inject

class CareerRepositoryImpl @Inject constructor() : CareerRepository {
    override val companies: Flow<List<CompanyVO>>
        get() = flow {
            emit(getCompanies())
        }

    private fun getCompanies(): List<CompanyVO>{
        val today : Long = System.currentTimeMillis()
        val oneYear : Long = (1000L * 60 * 60 * 24 * 365)
        val oneYearAgo : Long = today - oneYear
        val twoYearsAgo : Long = oneYearAgo - oneYear
        return listOf(
            CompanyVO(
                id = 1,
                name = "11번가",
                started = oneYearAgo,
                ended = today,
                role = "Android 개발자",
                projects = getProjects()
            ),
            CompanyVO(
                id = 2,
                name = "10번가",
                started = twoYearsAgo - oneYear,
                ended = oneYearAgo,
                role = "Android 개발자",
                projects = getProjects()
            ),
        )
    }

    private fun getProjects(): List<ProjectVO>{
        return listOf(
            ProjectVO(
                id = 1,
                name = "전사 디자인 시스템 구축",
                description = "회사 전체에서 사용할 수 있는 통합 디자인 시스템과 컴포넌트 라이브러리를 구축했습니다. 재사용 가능한 컴포넌트를 개발하여 개발 속도를 향상시켰습니다",
                imageUrl = "https://images.unsplash.com/photo-1551650975-87deedd944c3?w=800&h=600&fit=crop",
                performance = listOf("개발 시간 40% 단축", "디자인 일관성 95% 달성"),
                skills = listOf("kotlin", "Android")
            ),
            ProjectVO(
                id = 2,
                name = "AI 추천 시스템 도입",
                description = "머신러닝 기반의 개인화 추천 엔진을 프론트엔드에 통합하여 사용자 경험을 개선했습니다.",
                imageUrl = "https://images.unsplash.com/photo-1551650975-87deedd944c3?w=800&h=600&fit=crop",
                performance = listOf("사용자 참여도 45% 증가", "평균 세션 시간 2배 증가", "CTR 30% 향상"),
                skills = listOf("React", "TensorFlow.js", "GraphQL", "Redis")
            ),
            ProjectVO(
                id = 3,
                name = "모바일 앱 리뉴얼",
                description = "React Native를 사용하여 iOS/Android 앱을 완전히 재구축했습니다. 새로운 UI/UX 디자인을 적용하고 성능을 최적화했습니다.",
                imageUrl = "https://images.unsplash.com/photo-1551650975-87deedd944c3?w=800&h=600&fit=crop",
                performance = listOf("앱 평점 3.2 → 4.7 상승 ", "크래시율 80% 감소", "MAU 50% 증가"),
                skills = listOf("React", "TypeScript", "Redux", "Firebase")
            )
        )
    }
}