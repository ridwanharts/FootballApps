package com.jangkriek.ridwanharts.football.View.team


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import com.jangkriek.ridwanharts.football.Adapter.mainAdapter.MainTeamAdapter
import com.jangkriek.ridwanharts.football.Api.ApiRepository
import com.jangkriek.ridwanharts.football.Model.TeamPresenter
import com.jangkriek.ridwanharts.football.R
import com.jangkriek.ridwanharts.football.R.id.search_team
import com.jangkriek.ridwanharts.football.Model.Team
import com.jangkriek.ridwanharts.football.View.TeamDetailActivity
import com.jangkriek.ridwanharts.football.View.TeamView
import kotlinx.android.synthetic.main.main_fragment_teams.*
import org.jetbrains.anko.appcompat.v7.coroutines.onQueryTextListener
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamsFragment : Fragment(), TeamView {

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: MainTeamAdapter
    private lateinit var spinner: Spinner
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var leagueNmae: String
    private lateinit var searchView: SearchView

    override fun showLoading() {

        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {

        progressBar.visibility = View.INVISIBLE
    }

    override fun showTeamList(data: List<Team>) {

        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
        hideLoading()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progressBar = pb_teams
        swipeRefresh = swipe_teams
        spinner = spinner_teams

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter
        adapter = MainTeamAdapter(teams){
            context?.startActivity<TeamDetailActivity>("team_data" to it)
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                leagueNmae = spinner.selectedItem.toString()
                presenter.getTeamList(leagueNmae)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        recyclerView = rv_teams
        recyclerView.layoutManager = LinearLayoutManager(ctx)
        recyclerView.adapter =adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this, request, gson)

        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueNmae)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_fragment_teams, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater!!.inflate(R.menu.search_team, menu)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search_team)
        searchView = searchItem?.actionView as SearchView
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView?.setIconifiedByDefault(false)
        searchView?.queryHint = "Search Team..."
        searchView?.onQueryTextListener { onQueryTextChange {it ->
            presenter.getSearchTeam(it)
            true
        } }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId){
            search_team -> {
                true
            }else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
}
