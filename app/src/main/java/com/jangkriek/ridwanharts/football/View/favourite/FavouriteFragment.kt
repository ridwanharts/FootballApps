package com.jangkriek.ridwanharts.football.View.favourite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jangkriek.ridwanharts.football.Adapter.mainAdapter.MainFavouriteAdapter
import com.jangkriek.ridwanharts.football.Adapter.mainAdapter.MainMatchAdapter
import com.jangkriek.ridwanharts.football.R
import kotlinx.android.synthetic.main.main_fragment_favourite.*
import kotlinx.android.synthetic.main.main_fragment_match.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FavouriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_fragment_favourite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewpager_favourite.adapter = MainFavouriteAdapter(childFragmentManager)
        tab_favourite.setupWithViewPager(viewpager_favourite)
    }


}
