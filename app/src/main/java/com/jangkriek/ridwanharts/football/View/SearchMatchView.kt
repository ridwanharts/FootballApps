package com.jangkriek.ridwanharts.football.View

import com.jangkriek.ridwanharts.football.Model.Match

interface SearchMatchView {
    fun showProgress()
    fun hideProgress()
    fun showListMatch(match: List<Match>)
}