package com.example.playoffs.service


import com.example.playoffs.model.TeamResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FootballApi  {
    @Headers("x-apisports-key: YOUR_API_KEY")
    @GET("teams")
    fun getTeams(
        @Query("league") leagueId: Int,
        @Query("season") season: Int
    ) : Call <TeamResponse>
}