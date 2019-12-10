package com.jangkriek.ridwanharts.football.View

import android.widget.ProgressBar
import com.jangkriek.ridwanharts.football.Model.Team

interface DetailTeamView{
    fun showProgress(progressBar: ProgressBar)
    fun hideProgress(progressBar: ProgressBar)
    fun showDetailTeam(data: List<Team>)
}