package kr.yooreka.jafar.domain.repository

import kr.yooreka.jafar.domain.model.ProfileVO

interface ProfileRepository {
    //값이 바뀌지 않으므로 flow가 아닌 suspend 함수로만 구현
    suspend fun getProfile() : Result<ProfileVO>
}