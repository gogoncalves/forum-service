package domain

import java.time.LocalDateTime

data class User (
    val username: String,
    val password: String,
    val email: String,
    val createdDate: LocalDateTime
)