package kr.yooreka.jafar.domain.model

data class ProfileVO(
    val name: String,
    val imgUrl: String,
    val position: String,
    val skills: List<String>,
    val description: String,
    val mail: String,
    val linkedin: String,
    val github: String,
)