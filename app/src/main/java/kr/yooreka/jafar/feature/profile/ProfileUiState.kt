package kr.yooreka.jafar.feature.profile

import kr.yooreka.jafar.domain.model.ProfileVO

data class ProfileUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val aboutMe: AboutMeUIState? = null,
    val contact: ContactUIState? = null,
    val introduce: IntroduceUIState? = null
)

fun ProfileVO.toProfileUiState() =
    ProfileUiState(
        isLoading = false,
        errorMessage = null,
        aboutMe = AboutMeUIState(
            name = name,
            imgUrl = imgUrl,
            position = position,
            skills = skills
        ),
        contact = ContactUIState(
            mail = mail,
            linkedin = linkedin,
            github = github
        ),
        introduce = IntroduceUIState(
            description = description
        )
    )


data class AboutMeUIState(
    val name: String = "홍길동",
    val imgUrl: String? = null,
    val position: String = "",
    val skills: List<String> = emptyList()
)

data class ContactUIState(
    val mail: String = "",
    val linkedin: String = "",
    val github: String = ""
)

data class IntroduceUIState(
    val description: String = ""
)
