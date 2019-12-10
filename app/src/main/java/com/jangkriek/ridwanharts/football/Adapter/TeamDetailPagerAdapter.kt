package com.jangkriek.ridwanharts.football.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.jangkriek.ridwanharts.football.View.Team_OverviewFragment
import com.jangkriek.ridwanharts.football.View.Team_PlayerFragment

class TeamDetailPagerAdapter(fm: FragmentManager,
                             private val descriptionTeam: String?,
                             private val idTeam: String?):FragmentPagerAdapter(fm){
    override fun getItem(p0: Int): Fragment {
        return when(p0){
            0 -> Team_OverviewFragment()
            else -> Team_PlayerFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "Overview Team"
            else -> "Team Players"
        }
    }
}