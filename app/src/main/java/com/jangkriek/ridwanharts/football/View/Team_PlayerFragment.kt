package com.jangkriek.ridwanharts.football.View


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.jangkriek.ridwanharts.football.Adapter.PlayerAdapter
import com.jangkriek.ridwanharts.football.Api.ApiRepository
import com.jangkriek.ridwanharts.football.Model.Player
import com.jangkriek.ridwanharts.football.Model.PlayerPresenter
import com.jangkriek.ridwanharts.football.Model.Team
import com.jangkriek.ridwanharts.football.R
import kotlinx.android.synthetic.main.fragment_team__player.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.startActivity


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Team_PlayerFragment : Fragment(), PlayerView {

    private var players: MutableList<Player> = mutableListOf()
    private lateinit var teams: Team
    private lateinit var presenter: PlayerPresenter
    private lateinit var adapter: PlayerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var id: String? = null


    override fun showProgress() {

        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {

        progressBar.visibility = View.INVISIBLE
    }

    override fun showPlayerList(data: List<Player>) {

        swipeRefresh.isRefreshing = false
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
        hideProgress()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        teams = activity?.intent!!.getParcelableExtra("team_data")
        id = teams.teamId
        progressBar = pb_player
        swipeRefresh = swipe_player

        adapter = PlayerAdapter(players){
            ctx.startActivity<DetailPlayerActivity>("player_data" to it )
        }

        recyclerView = rv_player
        recyclerView.layoutManager = LinearLayoutManager(ctx)
        recyclerView.adapter =adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, request, gson)
        showPlayer()
        swipeRefresh.onRefresh {
            players.clear()
            showPlayer()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team__player, container, false)
    }

    private fun showPlayer(){
        swipe_player.isRefreshing = false
        presenter.getPlayerList(id)

    }

}
