package com.jangkriek.ridwanharts.football.Adapter.favoritAdapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jangkriek.ridwanharts.football.Database.FavouriteMatch
import com.jangkriek.ridwanharts.football.R
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

class FavouriteMatchAdapter(private val favouriteMatch: List<FavouriteMatch>,
                            private val listener: (FavouriteMatch)->Unit):RecyclerView.Adapter<FavouriteMatchAdapter.FavouriteHolder>(){
    override fun onBindViewHolder(p0: FavouriteHolder, p1: Int) {
        p0.bindItem(favouriteMatch[p1],listener)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavouriteHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_match, p0, false)
        return FavouriteHolder(
            view
        )
    }

    override fun getItemCount(): Int = favouriteMatch.size

    class FavouriteHolder(view: View): RecyclerView.ViewHolder(view){
        val dateMatch : TextView = view.find(R.id.tv_date)
        val homeTeam : TextView = view.find(R.id.tv_thome)
        val homeScore : TextView = view.find(R.id.tv_schome)
        val awayTeam : TextView = view.find(R.id.tv_taway)
        val awayScore : TextView = view.find(R.id.tv_scaway)

        fun bindItem(itemFavourite: FavouriteMatch, listener: (FavouriteMatch)-> Unit){
            dateMatch.text = itemFavourite.dateEvent
            homeTeam.text = itemFavourite.homeTeam
            homeScore.text = itemFavourite.homeScore
            awayTeam.text = itemFavourite.awayTeam
            awayScore.text = itemFavourite.awayScore

            itemView.onClick {
                listener(itemFavourite)
            }
        }
    }
}