package domain

import java.time.LocalDateTime

data class Comment (
    val content: String,
    var author: User,
    var publicationDate: LocalDateTime,
    var article: Article
)