package com.jangkriek.ridwanharts.football.Model

import com.google.gson.Gson
import com.jangkriek.ridwanharts.football.Api.ApiRepository
import com.jangkriek.ridwanharts.football.Api.TheSportsDBApi
import com.jangkriek.ridwanharts.football.CoroutineContextProvider
import com.jangkriek.ridwanharts.football.View.TeamView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync

class TeamPresenter(
    private val teamView: TeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getTeamList(event: String?){
        teamView.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getTeams(event)),
            TeamResponse::class.java)

            teamView.showTeamList(data.teams)
            teamView.hideLoading()
        }
    }

    /*fun getTeamList(league: String?){
        teamView.showLoading()
        doAsync{
            val data =
                gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getTeams(league)),
                    TeamResponse::class.java)

            teamView.showTeamList(data.teams)
            teamView.hideLoading()
        }
    }*/

    fun getSearchTeam(event: String?){
        teamView.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.searchTeam(event)),
                TeamResponse::class.java)

            teamView.showTeamList(data.teams)
            teamView.hideLoading()
        }
    }
}