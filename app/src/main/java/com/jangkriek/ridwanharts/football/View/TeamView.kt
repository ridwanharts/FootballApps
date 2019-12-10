package com.jangkriek.ridwanharts.football.View

import com.jangkriek.ridwanharts.football.Model.Team

interface TeamView{
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)


}