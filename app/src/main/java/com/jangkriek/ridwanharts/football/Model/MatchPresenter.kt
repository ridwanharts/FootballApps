package com.jangkriek.ridwanharts.football.Model

import android.util.Log
import com.google.gson.Gson
import com.jangkriek.ridwanharts.football.Api.ApiRepository
import com.jangkriek.ridwanharts.football.Api.TheSportsDBApi
import com.jangkriek.ridwanharts.football.CoroutineContextProvider
import com.jangkriek.ridwanharts.football.View.MatchView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter (private val matchView: MatchView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
){


    /*fun getNextMatchData(league: String?){
        doAsync {
            val dataMatch = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getNextMatch(league)), MatchResponse::class.java)
            uiThread { matchView.showDataMatchList(dataMatch.events) }
        }
    }*/

    fun getNextMatchData(league: String?){
        GlobalScope.launch(context.main) {
            val match = gson.fromJson(apiRepository
                .doRequest(TheSportsDBApi.getNextMatch(league)), MatchResponse::class.java)

            Log.d("Debug","Hasil NextM : "+ match.events?.let { matchView.showDataMatchList(it) })
            match.events?.let { matchView.showDataMatchList(it) }
            //matchView.showDataMatchList(match.events)
        }
    }

    fun getPreviousMatchData(league: String?){
        doAsync {
            val dataMatch = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getPreviousMatch(league)), MatchResponse::class.java)
            uiThread {
                dataMatch.events?.let { matchView.showDataMatchList(it) }
                //matchView.showDataMatchList(dataMatch.events)
            }
        }
    }
}