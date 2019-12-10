package com.jangkriek.ridwanharts.football.View.match


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.jangkriek.ridwanharts.football.Adapter.mainAdapter.MainMatchAdapter
import com.jangkriek.ridwanharts.football.R
import com.jangkriek.ridwanharts.football.View.SearchMatchActivity
import kotlinx.android.synthetic.main.main_fragment_match.*
import org.jetbrains.anko.support.v4.startActivity


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentMatch : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_fragment_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        viewpager_match.adapter = MainMatchAdapter(childFragmentManager)
        tab_match.setupWithViewPager(viewpager_match)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.search_match, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.search_match -> {
                startActivity<SearchMatchActivity>()
                true
            }else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}
