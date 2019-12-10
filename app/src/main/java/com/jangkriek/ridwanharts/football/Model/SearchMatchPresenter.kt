package com.jangkriek.ridwanharts.football.Model

import android.util.Log
import com.google.gson.Gson
import com.jangkriek.ridwanharts.football.Api.ApiRepository
import com.jangkriek.ridwanharts.football.Api.TheSportsDBApi
import com.jangkriek.ridwanharts.football.CoroutineContextProvider
import com.jangkriek.ridwanharts.football.View.SearchMatchView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMatchPresenter(
    private val search: SearchMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getSearchMatch(event: String?) {
        search.showProgress()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.searchMatch(event)),
                EventResponse::class.java)
            Log.d("Debug","Hasil Search : "+data.event?.let { search.showListMatch(it) })
            data.event?.let { search.showListMatch(it) }
            //search.showListMatch(data.events)
            search.hideProgress()
        }
    }

    /*fun getSearchMatch(event: String?){
        search.showProgress()
        doAsync{
            val data =
                gson.fromJson(apiRepository.doRequest(TheSportsDBApi.searchMatch(event)),
                    MatchResponse::class.java)

            search.showListMatch(data.events)
            search.hideProgress()
        }
    }*/
}