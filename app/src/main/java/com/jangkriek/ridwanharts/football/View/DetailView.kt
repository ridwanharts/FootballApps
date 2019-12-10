package com.jangkriek.ridwanharts.football.View

import com.jangkriek.ridwanharts.football.Model.Badge
import com.jangkriek.ridwanharts.football.Model.DetailMatch

interface DetailView{

    fun detailMatch (data: List<DetailMatch>)

    fun badgeHomeTeam(data: List<Badge>)

    fun badgeAwayTeam(data: List<Badge>)
}