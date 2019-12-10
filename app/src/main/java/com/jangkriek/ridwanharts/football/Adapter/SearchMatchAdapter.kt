package com.jangkriek.ridwanharts.football.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jangkriek.ridwanharts.football.Model.Match
import com.jangkriek.ridwanharts.football.R
import kotlinx.android.synthetic.main.item_match.view.*
import java.text.SimpleDateFormat

class SearchMatchAdapter(private var match: List<Match>,
                         private val listener: (Match)-> Unit): RecyclerView.Adapter<ViewHolderSearch>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolderSearch {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_match, p0, false)
        return ViewHolderSearch(view)
    }

    override fun getItemCount(): Int = match.size

    override fun onBindViewHolder(p0: ViewHolderSearch, p1: Int) {

        p0.bindItem(match[p1], listener)
    }
}

class ViewHolderSearch(val view: View): RecyclerView.ViewHolder(view){
    fun bindItem(match: Match, listener: (Match) -> Unit){

        val dateParse = SimpleDateFormat("yyyy-MM-dd").parse(match.matchDate)
        val dateMatch = SimpleDateFormat("E, dd MMM yyyy").format(dateParse)

        itemView.tv_date.text = dateMatch
        itemView.tv_thome.text = match.teamHome
        itemView.tv_schome.text = match.scoreHome
        itemView.tv_taway.text = match.teamAway
        itemView.tv_scaway.text = match.scoreAway

        itemView.setOnClickListener {
            listener(match)
        }
    }
}