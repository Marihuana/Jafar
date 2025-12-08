package kr.yooreka.jafar.data.dto

import kr.yooreka.jafar.domain.model.ProfileVO

data class ProfileDTO(
    val name: String,
    val imgUrl: String,
    val position: String,
    val skills: List<String>,
    val description: String,
    val mail: String,
    val linkedin: String,
    val github: String,
){
    fun toProfileVO() = ProfileVO(
        name = name,
        imgUrl = imgUrl,
        position = position,
        skills = skills,
        description = description,
        mail = mail,
        linkedin = linkedin,
        github = github,
    )
}
