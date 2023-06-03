package domain

import java.time.LocalDateTime

data class Comment (
    val content: String,
    val author: User,
    val publicationDate: LocalDateTime,
    val article: Article
)