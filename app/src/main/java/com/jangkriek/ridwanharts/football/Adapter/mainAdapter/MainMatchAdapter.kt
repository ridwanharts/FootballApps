package com.jangkriek.ridwanharts.football.Adapter.mainAdapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.jangkriek.ridwanharts.football.R
import com.jangkriek.ridwanharts.football.View.match.FragmentMatch
import com.jangkriek.ridwanharts.football.View.match.NextMatchFragment
import com.jangkriek.ridwanharts.football.View.match.PreviousMatchFragment

class MainMatchAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {

        return when(p0){
            0 -> {
                NextMatchFragment()

            }
            else -> {
                PreviousMatchFragment()

            }
        }
    }

    override fun getCount(): Int {
        return 2

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> {
                "Next Match"
            }
            else -> {
                "Previous Match"
            }
        }
    }

}