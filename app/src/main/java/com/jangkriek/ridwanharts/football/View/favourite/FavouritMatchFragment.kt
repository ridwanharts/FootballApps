package com.jangkriek.ridwanharts.football.View.favourite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jangkriek.ridwanharts.football.Database.FavouriteMatch
import com.jangkriek.ridwanharts.football.Database.database
import com.jangkriek.ridwanharts.football.Adapter.favoritAdapter.FavouriteMatchAdapter
import com.jangkriek.ridwanharts.football.R
import com.jangkriek.ridwanharts.football.View.DetailActivity
import kotlinx.android.synthetic.main.favourit_fragment_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FavouritMatchFragment : Fragment(){

    private var favMatch: MutableList<FavouriteMatch> = mutableListOf()
    private lateinit var matchAdapter: FavouriteMatchAdapter
    private lateinit var recycleView : RecyclerView
    private lateinit var swipe: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.favourit_fragment_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        matchAdapter =
                FavouriteMatchAdapter(favMatch) {
                    ctx.startActivity<DetailActivity>(
                        "id_event" to "${it.idEvent}",
                        "home_team" to "${it.homeTeam}",
                        "home_score" to "${it.homeScore}",
                        "away_team" to "${it.awayTeam}",
                        "away_score" to "${it.awayScore}",
                        "date_event" to "${it.dateEvent}"
                    )
                }

        recycleView = rv_favourite
        recycleView.layoutManager = LinearLayoutManager(ctx)
        recycleView.adapter = matchAdapter
        showFavouriteMatch()

        swipe_lay.onRefresh {
            favMatch.clear()
            showFavouriteMatch()

        }
    }

    private fun showFavouriteMatch(){
        context?.database?.use{
            swipe_lay.isRefreshing = false
            val result = select(FavouriteMatch.TABLE_FAVOURITE)
            val favourite = result.parseList(classParser<FavouriteMatch>())
            favMatch.addAll(favourite)
            matchAdapter.notifyDataSetChanged()
        }
    }
}
