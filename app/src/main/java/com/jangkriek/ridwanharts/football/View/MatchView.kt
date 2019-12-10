package com.jangkriek.ridwanharts.football.View

import com.jangkriek.ridwanharts.football.Model.Match


interface MatchView {

    fun showDataMatchList(data: List<Match>)
}