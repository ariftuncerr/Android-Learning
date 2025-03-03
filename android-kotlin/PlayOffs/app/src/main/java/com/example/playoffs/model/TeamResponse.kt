package com.example.playoffs.model

data class TeamResponse (
    val response: List<TeamInfo>
)
data class TeamInfo (
    val team: Team,
    val league: League,
    val rating: Int
)
data class Team(
    val id: Int,
    val name: String,
    val logo: String

)
data class League(
    val id: Int,
    val name: String
)
