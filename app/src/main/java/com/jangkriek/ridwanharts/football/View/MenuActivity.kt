package com.jangkriek.ridwanharts.football.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.jangkriek.ridwanharts.football.View.favourite.FavouriteFragment
import com.jangkriek.ridwanharts.football.Model.Match
import com.jangkriek.ridwanharts.football.R
import com.jangkriek.ridwanharts.football.View.team.TeamsFragment
import com.jangkriek.ridwanharts.football.View.match.FragmentMatch
import com.jangkriek.ridwanharts.football.View.match.NextMatchFragment
import com.jangkriek.ridwanharts.football.View.match.PreviousMatchFragment
import kotlinx.android.synthetic.main.activity_menu.*
import org.jetbrains.anko.startActivity

class MenuActivity : AppCompatActivity(), NextMatchFragment.OnFragmentInteractionListener, PreviousMatchFragment.OnFragmentInteractionListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        setupViewFragment(FragmentMatch())
        bottomNav.setOnNavigationItemSelectedListener (navigationFragment)
    }

    private fun setupViewFragment(fragment: Fragment){
        val fm = supportFragmentManager.beginTransaction()
        fm.replace(R.id.fr_container, fragment)
        fm.addToBackStack(null)
        fm.commit()
    }



    override fun OnFragmentInteraction(item: Match) {
        startActivity<DetailActivity>(
            "id_event" to item.eventId,
            "date_event" to item.matchDate,
            "home_team" to item.teamHome,
            "home_score" to item.scoreHome,
            "away_team" to item.teamAway,
            "away_score" to item.scoreAway

            )
    }

    private val navigationFragment = BottomNavigationView.OnNavigationItemSelectedListener {
            item -> when(item.itemId){
            R.id.n_match -> {
                val fr = FragmentMatch()
                setupViewFragment(fr)
                return@OnNavigationItemSelectedListener true
            }
            R.id.n_teams-> {
                val fr = TeamsFragment()
                setupViewFragment(fr)
                return@OnNavigationItemSelectedListener true
            }
            R.id.n_favourite -> {
                val fr = FavouriteFragment()
                setupViewFragment(fr)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
