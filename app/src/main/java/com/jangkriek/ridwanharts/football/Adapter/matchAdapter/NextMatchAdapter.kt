package com.jangkriek.ridwanharts.football.Adapter.matchAdapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jangkriek.ridwanharts.football.Model.Match
import com.jangkriek.ridwanharts.football.View.match.NextMatchFragment
import com.jangkriek.ridwanharts.football.R
import org.jetbrains.anko.find

class NextMatchAdapter(private val dataItems: MutableList<Match>,
                       private val listener: NextMatchFragment.OnFragmentInteractionListener?): RecyclerView.Adapter<NextMatchAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_match, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataItems.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(dataItems[p1], listener)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val dateMatch : TextView = view.find(R.id.tv_date)
        val teamHome : TextView = view.find(R.id.tv_thome)
        val teamAway : TextView = view.find(R.id.tv_taway)
        val scoreHome : TextView = view.find(R.id.tv_schome)
        val scoreAway : TextView = view.find(R.id.tv_scaway)

        fun bindItem(item: Match, listener: NextMatchFragment.OnFragmentInteractionListener?){
            dateMatch.text = item.matchDate
            teamHome.text = item.teamHome
            teamAway.text = item.teamAway
            scoreHome.text = item.scoreHome
            scoreAway.text = item.scoreAway

            itemView.setOnClickListener {
                listener?.OnFragmentInteraction(item)
            }
        }

    }
}