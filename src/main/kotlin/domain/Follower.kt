package domain

import java.time.LocalDateTime

data class Follower (
    val follower: User,
    val following: User,
    val startDate: LocalDateTime
)