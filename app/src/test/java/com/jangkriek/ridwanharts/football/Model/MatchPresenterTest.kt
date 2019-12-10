package com.jangkriek.ridwanharts.football.Model

import com.google.gson.Gson
import com.jangkriek.ridwanharts.football.Api.ApiRepository
import com.jangkriek.ridwanharts.football.Api.TheSportsDBApi
import com.jangkriek.ridwanharts.football.TestContextProvider
import com.jangkriek.ridwanharts.football.View.DetailView
import com.jangkriek.ridwanharts.football.View.MatchView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MatchPresenterTest {

    @Mock
    private lateinit var view: MatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    //Skenario MatchPresenterTest untuk test request url mendapatkan data next match
    @Test
    fun getNextMatchData() {
        val match: MutableList<Match> = mutableListOf()
        val response = MatchResponse(match)
        val eMatch = ""
        GlobalScope.launch {
            `when`(
                gson.fromJson(apiRepository
                    .doRequest(TheSportsDBApi.getNextMatch(eMatch)),
                    MatchResponse::class.java)
            ).thenReturn(response)
            presenter.getNextMatchData(eMatch)
            Mockito.verify(view).showDataMatchList(match)
        }
    }
}