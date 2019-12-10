package com.jangkriek.ridwanharts.football.Model

import com.google.gson.Gson
import com.jangkriek.ridwanharts.football.Api.ApiRepository
import com.jangkriek.ridwanharts.football.Api.TheSportsDBApi
import com.jangkriek.ridwanharts.football.TestContextProvider
import com.jangkriek.ridwanharts.football.View.DetailView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DetailMatchPresenterTest {

    @Mock
    private lateinit var view: DetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: DetailMatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    //Skenario MatchPresenterTest untuk test request url mendapatkan detail match
    @Test
    fun getMatchDetail() {
        val detail: MutableList<DetailMatch> = mutableListOf()
        val response = DetailResponse(detail)
        val event = ""
        GlobalScope.launch {
            `when`(
                gson.fromJson(apiRepository
                    .doRequest(TheSportsDBApi.getDetailMatch(event)),
                    DetailResponse::class.java
                )
            ).thenReturn(response)
            presenter.getMatchDetail(event)

            Mockito.verify(view).detailMatch(detail)
        }
    }
}