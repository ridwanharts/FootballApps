package com.jangkriek.ridwanharts.football.Model

import com.google.gson.Gson
import com.jangkriek.ridwanharts.football.Api.ApiRepository
import com.jangkriek.ridwanharts.football.Api.TheSportsDBApi
import com.jangkriek.ridwanharts.football.CoroutineContextProvider
import com.jangkriek.ridwanharts.football.View.DetailView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailMatchPresenter(
    public val detailView: DetailView,
    public val apiRepository: ApiRepository,
    public val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getBadge(team: String?, teamType: String?){
        doAsync {
            val dataTeam = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getBadge(team)), BadgeResponse::class.java)

            uiThread {
                if(teamType == "Home"){
                    detailView.badgeHomeTeam(dataTeam.teams)
                }else{
                    detailView.badgeAwayTeam(dataTeam.teams)
                }
            }

        }
    }

    /*fun getMatchDetail(event: String?){
        doAsync {
            val dataDetail = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getDetailMatch(event)), DetailResponse::class.java)

            uiThread { detailView.detailMatch(dataDetail.events) }

        }
    }*/

    fun getMatchDetail(event: String?){
        GlobalScope.launch(context.main) {
            val detail = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getDetailMatch(event)), DetailResponse::class.java)
            detailView.detailMatch(detail.events)
        }
    }
}