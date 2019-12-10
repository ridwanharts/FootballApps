package com.jangkriek.ridwanharts.football.View

import com.jangkriek.ridwanharts.football.Model.Player

interface PlayerView{
    fun showProgress()
    fun hideProgress()
    fun showPlayerList(data: List<Player>)
}