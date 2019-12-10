package com.jangkriek.ridwanharts.football.View.match


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.gson.Gson
import com.jangkriek.ridwanharts.football.Adapter.matchAdapter.PreviousMatchAdapter
import com.jangkriek.ridwanharts.football.Api.ApiRepository
import com.jangkriek.ridwanharts.football.Model.Match
import com.jangkriek.ridwanharts.football.Model.MatchPresenter
import com.jangkriek.ridwanharts.football.R
import com.jangkriek.ridwanharts.football.View.MatchView
import kotlinx.android.synthetic.main.match_fragment_next.view.*
import kotlinx.android.synthetic.main.match_fragment_previous.view.*
import org.jetbrains.anko.support.v4.ctx


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PreviousMatchFragment : Fragment(), MatchView {

    private var dataItems: MutableList<Match> = mutableListOf()
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var matchPresenter : MatchPresenter
    private lateinit var adapter: PreviousMatchAdapter
    private lateinit var spinner: Spinner
    private lateinit var leagueName: String
    private lateinit var idLeague: String

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener){
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener{
        fun OnFragmentInteraction(item: Match)
    }

    companion object {
        @JvmStatic
        fun newInstance() = PreviousMatchFragment()
    }

    override fun showDataMatchList(data: List<Match>) {
        dataItems.clear()
        dataItems.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.match_fragment_previous, container, false)
        val rv = view.findViewById<RecyclerView>(R.id.rv_list_p)
        rv.layoutManager = LinearLayoutManager(context)
        adapter = PreviousMatchAdapter(dataItems, listener)
        rv.adapter = adapter
        val api = ApiRepository()
        val gson = Gson()
        matchPresenter = MatchPresenter(this, api, gson)

        spinner = view.spinner_prev_match
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                if (leagueName.equals("Spanish La Liga")){
                    idLeague = "4335"
                }else if (leagueName.equals("English Premier League")){
                    idLeague = "4328"

                }else if (leagueName.equals("English League Championship")){
                    idLeague = "4329"

                }else if (leagueName.equals("German Bundesliga")){
                    idLeague = "4331"

                }else if (leagueName.equals("Italian Serie A")){
                    idLeague = "4332"

                }else if (leagueName.equals("French Ligue 1")){
                    idLeague = "4334"
                }

                matchPresenter.getPreviousMatchData(idLeague)

            }
        }

        return view
    }


}
