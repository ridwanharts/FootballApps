package com.jangkriek.ridwanharts.football.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.google.gson.Gson
import com.jangkriek.ridwanharts.football.Api.ApiRepository
import com.jangkriek.ridwanharts.football.Model.Event
import com.jangkriek.ridwanharts.football.Model.Match
import com.jangkriek.ridwanharts.football.R
import com.jangkriek.ridwanharts.football.Adapter.SearchMatchAdapter
import com.jangkriek.ridwanharts.football.Model.SearchMatchPresenter
import kotlinx.android.synthetic.main.activity_search_match.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onQueryTextListener
import org.jetbrains.anko.support.v4.onRefresh

class SearchMatchActivity : AppCompatActivity(), SearchMatchView {

    private var match: MutableList<Match> = mutableListOf()
    private var event: MutableList<Event> = mutableListOf()

    private lateinit var presenter: SearchMatchPresenter
    private lateinit var adapter: SearchMatchAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar



    override fun showProgress() {

        pb_search_match.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pb_search_match.visibility = View.INVISIBLE

    }

    override fun showListMatch(match: List<Match>) {

        this.match.clear()
        this.match.addAll(match)
        adapter.notifyDataSetChanged()
        hideProgress()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        supportActionBar?.hide()
        search_view_match.onActionViewExpanded()
        search_view_match.onQueryTextListener {
            onQueryTextChange { it ->
                presenter.getSearchMatch(it)
            true
            }
            hideProgress()
        }

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = SearchMatchPresenter(this, apiRepository, gson)

        adapter = SearchMatchAdapter(match) {
            event.clear()
            event.add(
                Event(
                    it.eventId, it.matchDate, it.teamHome, it.scoreHome, "", it.teamAway, it.scoreAway, ""
                )
            )

            ctx.startActivity<DetailActivity>(
                "id_event" to match[0].eventId,
                "home_team" to match[0].teamHome,
                "home_score" to match[0].scoreHome,
                "away_team" to match[0].teamAway,
                "away_score" to match[0].scoreAway,
                "date_event" to match[0].matchDate
            )
        }

        recyclerView = rv_search_match
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        swipe_search_match.onRefresh {
            presenter.getSearchMatch("Arsenal")
            hideProgress()
        }
    }
}
