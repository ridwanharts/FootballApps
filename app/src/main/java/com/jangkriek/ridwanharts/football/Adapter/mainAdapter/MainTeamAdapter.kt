package com.jangkriek.ridwanharts.football.Adapter.mainAdapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jangkriek.ridwanharts.football.R
import com.jangkriek.ridwanharts.football.Model.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainTeamAdapter(private val teams: List<Team>,
                      private val listener: (Team)->Unit): RecyclerView.Adapter<MainTeamAdapter.TeamViewHolder>(){
    override fun onBindViewHolder(p0: TeamViewHolder, p1: Int) {
        p0.bindItem(teams[p1],listener)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_team, p0, false)
        return TeamViewHolder(view)
    }

    override fun getItemCount(): Int = teams.size

    class TeamViewHolder(view: View): RecyclerView.ViewHolder(view){
        val teamBadge : ImageView = view.find(R.id.iv_fav_team)
        val teamName : TextView = view.find(R.id.tv_fav_team)

        fun bindItem(teams: Team, listener: (Team)-> Unit){

            Picasso.get().load(teams.teamBadge).into(teamBadge)
            teamName.text = teams.teamName

            itemView.onClick {
                listener(teams)
            }
        }
    }
}