package com.jangkriek.ridwanharts.football.Adapter.mainAdapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.jangkriek.ridwanharts.football.View.favourite.FavouritMatchFragment
import com.jangkriek.ridwanharts.football.View.favourite.FavouriteFragmentTeams
import com.jangkriek.ridwanharts.football.View.match.NextMatchFragment
import com.jangkriek.ridwanharts.football.View.match.PreviousMatchFragment

class MainFavouriteAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {

        return when(p0){
            0 -> {
                FavouritMatchFragment()

            }
            else -> {
                FavouriteFragmentTeams()

            }
        }
    }

    override fun getCount(): Int {
        return 2

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> {
                "Favourite Match"
            }
            else -> {
                "Favourite Teams"
            }
        }
    }
}