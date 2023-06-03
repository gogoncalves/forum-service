package domain

import java.time.LocalDateTime

data class Article (
    val title: String,
    val content: String,
    val author: User,
    val publicationDate: LocalDateTime
)