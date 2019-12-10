package com.jangkriek.ridwanharts.football.View.favourite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jangkriek.ridwanharts.football.Adapter.favoritAdapter.FavouriteTeamAdapter
import com.jangkriek.ridwanharts.football.Database.FavouriteTeam
import com.jangkriek.ridwanharts.football.Database.database
import com.jangkriek.ridwanharts.football.R
import com.jangkriek.ridwanharts.football.Model.Team
import com.jangkriek.ridwanharts.football.View.TeamDetailActivity
import kotlinx.android.synthetic.main.favourite_fragment_teams.*
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
class FavouriteFragmentTeams : Fragment(){

    private var favTeam: MutableList<Team> = mutableListOf()
    private lateinit var teamAdapter: FavouriteTeamAdapter
    private lateinit var recycleView : RecyclerView
    private lateinit var swipe: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.favourite_fragment_teams, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        teamAdapter =
                FavouriteTeamAdapter(favTeam) {
                    context?.startActivity<TeamDetailActivity>("team_data" to it)
                }

        recycleView = rv_favourite_team
        recycleView.layoutManager = LinearLayoutManager(ctx)
        recycleView.adapter = teamAdapter
        showFavouriteTeam()

        swipe_lay_team.onRefresh {
            favTeam.clear()
            showFavouriteTeam()

        }
    }

    private fun showFavouriteTeam(){
        context?.database?.use{
            swipe_lay_team.isRefreshing = false
            val result = select(FavouriteTeam.TABLE_FAVOURITE_TEAM)
            val favourite = result.parseList(classParser<Team>())
            favTeam.addAll(favourite)
            teamAdapter.notifyDataSetChanged()
        }
    }
}
