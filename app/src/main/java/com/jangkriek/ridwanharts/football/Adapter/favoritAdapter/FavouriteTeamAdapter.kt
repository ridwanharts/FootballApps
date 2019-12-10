package com.jangkriek.ridwanharts.football.Adapter.favoritAdapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jangkriek.ridwanharts.football.R
import com.jangkriek.ridwanharts.football.Model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_team.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class FavouriteTeamAdapter(private val favouriteTeam: List<Team>, private val listener:(Team)-> Unit): RecyclerView.Adapter<FavouriteTeamAdapter.ViewHolderFavouriteTeam>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolderFavouriteTeam {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_team, p0, false)
        return ViewHolderFavouriteTeam(view)
    }

    override fun getItemCount(): Int = favouriteTeam.size

    override fun onBindViewHolder(p0: ViewHolderFavouriteTeam, p1: Int) {
        p0.bindItem(favouriteTeam[p1], listener)
    }

    class ViewHolderFavouriteTeam(view: View): RecyclerView.ViewHolder(view){
        fun bindItem(itemFavvouriteTeam: Team, listener: (Team)-> Unit){
            itemView.tv_fav_team.text = itemFavvouriteTeam.teamName
            Picasso.get().load(itemFavvouriteTeam.teamBadge).into(itemView.iv_fav_team)
            itemView.onClick { listener(itemFavvouriteTeam) }
        }
    }
}