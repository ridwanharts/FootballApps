package com.jangkriek.ridwanharts.football.Model

import com.google.gson.Gson
import com.jangkriek.ridwanharts.football.Api.ApiRepository
import com.jangkriek.ridwanharts.football.Api.TheSportsDBApi
import com.jangkriek.ridwanharts.football.CoroutineContextProvider
import com.jangkriek.ridwanharts.football.View.PlayerView
import com.jangkriek.ridwanharts.football.View.TeamView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync

class PlayerPresenter(
    private val teamView: PlayerView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    /*fun getPlayerList(player: String?) {
        teamView.showProgress()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getPlayers(player)),
                PlayerResponse::class.java
            )

            teamView.showPlayerList(data.player)
            teamView.hideProgress()
        }
    }*/

    fun getPlayerList(player: String?) {
        teamView.showProgress()
        doAsync {
            val data =gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getPlayers(player)), PlayerResponse::class.java)

            teamView.showPlayerList(data.player)
            teamView.hideProgress()
        }
    }

}