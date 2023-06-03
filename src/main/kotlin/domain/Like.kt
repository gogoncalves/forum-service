package domain

import java.time.LocalDateTime

data class Like(
    val user: User,
    val article: Article,
    val date: LocalDateTime
)