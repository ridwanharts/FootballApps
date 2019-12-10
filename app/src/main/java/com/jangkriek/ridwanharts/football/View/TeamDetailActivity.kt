package com.jangkriek.ridwanharts.football.View

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.jangkriek.ridwanharts.football.Adapter.TeamDetailPagerAdapter
import com.jangkriek.ridwanharts.football.Database.FavouriteTeam
import com.jangkriek.ridwanharts.football.Database.database
import com.jangkriek.ridwanharts.football.Model.Team
import com.jangkriek.ridwanharts.football.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamDetailActivity : AppCompatActivity() {

    private var menuItem: Menu? = null
    private var isFav: Boolean = false
    private lateinit var team: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        team = intent.getParcelableExtra("team_data")

        favState()
        Picasso.get().load(team.teamBadge).into(img_team_detail)
        tv_team_name_detail.text = team.teamName
        tv_team_stadium_detail.text = team.teamStadium
        tv_team_year_detail.text = team.teamFormedYear

        viewpager_team_detail.adapter = TeamDetailPagerAdapter(supportFragmentManager, team.teamDescription, team.teamId)
        tab_team_detail.setupWithViewPager(viewpager_team_detail)

        //setSupportActionBar(toolbar_team_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = team.teamName
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_fav, menu)
        menuItem = menu
        setFav()
        return true
    }

    private fun setFav(){
        if(isFav){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
                R.drawable.ic_added_fav
            )
        }else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
                R.drawable.ic_add_fav
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when(item?.itemId){

            android.R.id.home -> {
                super.onBackPressed()
                true
            }
            R.id.fav -> {
                if(isFav){
                    removeFromFav()
                }else{
                    addToFav()
                }
                isFav=!isFav
                setFav()
                true
            }else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun addToFav(){
        try {
            database.use {
                insert(
                    FavouriteTeam.TABLE_FAVOURITE_TEAM,
                    FavouriteTeam.ID_TEAM to team.teamId,
                    FavouriteTeam.NAME_TEAM to team.teamName,
                    FavouriteTeam.IMAGE_TEAM to team.teamBadge,
                    FavouriteTeam.YEAR_TEAM to team.teamFormedYear,
                    FavouriteTeam.STADIUM_TEAM to team.teamStadium,
                    FavouriteTeam.DESC_TEAM to team.teamDescription
                )
            }
        } catch (e: SQLiteConstraintException) {

        }
    }

    private fun removeFromFav(){
        try {
            database.use {
                delete(FavouriteTeam.TABLE_FAVOURITE_TEAM, "( ID_TEAM = {id_team})",
                    "id_team" to team.teamId.toString())
            }
        } catch (e: SQLiteConstraintException) {

        }
    }

    private fun favState(){
        database.use{
            val result = select(FavouriteTeam.TABLE_FAVOURITE_TEAM).whereArgs("( ID_TEAM = {id_team})","id_team" to team.teamId.toString())
            val favouriteTeam = result.parseList(classParser<Team>())
            if (!favouriteTeam.isEmpty()){
                isFav=true
            }
        }
    }

}

