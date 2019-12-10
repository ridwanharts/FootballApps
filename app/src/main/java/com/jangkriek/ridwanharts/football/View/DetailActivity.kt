package com.jangkriek.ridwanharts.football.View

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toolbar
import com.google.gson.Gson
import com.jangkriek.ridwanharts.football.Api.ApiRepository
import com.jangkriek.ridwanharts.football.Database.FavouriteMatch
import com.jangkriek.ridwanharts.football.Database.database
import com.jangkriek.ridwanharts.football.Model.Badge
import com.jangkriek.ridwanharts.football.Model.DetailMatch
import com.jangkriek.ridwanharts.football.Model.DetailMatchPresenter
import com.jangkriek.ridwanharts.football.R
import com.jangkriek.ridwanharts.football.R.id.fav
import com.jangkriek.ridwanharts.football.R.drawable.ic_add_fav
import com.jangkriek.ridwanharts.football.R.drawable.ic_added_fav
import com.jangkriek.ridwanharts.football.R.menu.save_fav
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var detailMatchPresenter: DetailMatchPresenter
    private var menuItem: Menu? = null
    private var isFav: Boolean = false
    private lateinit var idEvent: String
    private var dateEvent: String? = null
    private var homeTeam: String? = null
    private var homeScore: String? = null
    private var awayTeam: String? = null
    private var awayScore: String? = null

    override fun detailMatch(data: List<DetailMatch>) {

        val hGoalDetails = getItem(data[0].homeGoalDetailsStr)
        val hGoalShot = getItem(data[0].homeShotsInt)
        val hForward = getItem(data[0].homeLineupForwardStr)
        val hMidfield = getItem(data[0].homeLineupMidfieldStr)
        val hDefense = getItem(data[0].homeLineupDefenseStr)
        val hGoalkeeper = getItem(data[0].homeLineupGoalKeeperStr)
        val hSubstitution = getItem(data[0].homeLineupSubstitutesStr)

        setToTextDetails(hGoalDetails, tv_home_pgoals)
        setToTextDetails(hGoalShot, tv_home_goals)
        setToTextDetails(hForward, tv_home_forward)
        setToTextDetails(hMidfield, tv_home_midfield)
        setToTextDetails(hDefense, tv_home_def)
        setToTextDetails(hGoalkeeper, tv_home_gk)
        setToTextDetails(hSubstitution, tv_home_sub)

        val aGoalDetails = getItem(data[0].awayGoalDetailsStr)
        val aGoalShot = getItem(data[0].awayShotsInt)
        val aForward = getItem(data[0].awayLineupForwardStr)
        val aMidfield = getItem(data[0].awayLineupMidfieldStr)
        val aDefense = getItem(data[0].awayLineupDefenseStr)
        val aGoalkeeper = getItem(data[0].awayLineupGoalKeeperStr)
        val aSubstitution = getItem(data[0].awayLineupSubstitutesStr)

        setToTextDetails(aGoalDetails, tv_away_pgoals)
        setToTextDetails(aGoalShot, tv_away_goals)
        setToTextDetails(aForward, tv_away_forward)
        setToTextDetails(aMidfield, tv_away_midfield)
        setToTextDetails(aDefense, tv_away_def)
        setToTextDetails(aGoalkeeper, tv_away_gk)
        setToTextDetails(aSubstitution, tv_away_sub)

    }

    override fun badgeHomeTeam(data: List<Badge>) {
        Picasso.get().load(data[0].badgeTeam).into(iv_home)
    }

    override fun badgeAwayTeam(data: List<Badge>) {
        Picasso.get().load(data[0].badgeTeam).into(iv_away)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent

        idEvent = intent.getStringExtra("id_event")
        dateEvent = intent.getStringExtra("date_event")

        homeTeam = intent.getStringExtra("home_team")
        homeScore = intent.getStringExtra("home_score")
        awayTeam = intent.getStringExtra("away_team")
        awayScore = intent.getStringExtra("away_score")

        date_match.text = dateEvent
        tv_home_detail.text = homeTeam
        tv_home_score.text = homeScore
        tv_away_detail.text = awayTeam
        tv_away_score.text = awayScore

        favState()

        val apiRepo = ApiRepository()
        val gson = Gson()
        detailMatchPresenter = DetailMatchPresenter(this, apiRepo, gson)
        detailMatchPresenter.getBadge(homeTeam, "Home")
        detailMatchPresenter.getBadge(awayTeam, "Away")
        detailMatchPresenter.getMatchDetail(idEvent)

        if (homeTeam.equals(null) || awayTeam.equals(null)){
            homeTeam = "Arsenal"
            awayTeam = "Chelsea"
        }
    }

    private fun getItem(data: String?): List<String>{
        return data.toString().split(";")
    }

    private fun getDataString(text: String?, value: String): String{
        return if (value != "null")
            getString(R.string.text, text, value)
        else
            getString(R.string.text, "", " - ")
    }

    private fun setToTextDetails(list: List<String>, text: TextView){
        for(value in list){
            text.text = getDataString(text.text.toString(), value.trim())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(save_fav, menu)
        menuItem = menu
        setFav()
        return true
    }

    private fun setFav(){
        if(isFav){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_fav)
        }else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_fav)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

            return when(item?.itemId){
                fav -> {
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
            database.use { insert(
                FavouriteMatch.TABLE_FAVOURITE,
                FavouriteMatch.ID_EVENT to idEvent,
                FavouriteMatch.DATE_EVENT to dateEvent,
                FavouriteMatch.HOME_TEAM to homeTeam,
                FavouriteMatch.HOME_SCORE to homeScore,
                FavouriteMatch.AWAY_TEAM to awayTeam,
                FavouriteMatch.AWAY_SCORE to awayScore
            ) }
        }catch (e: SQLiteConstraintException){

        }
    }

    private fun removeFromFav(){
        try {
            database.use {
                delete(FavouriteMatch.TABLE_FAVOURITE, "( ID_EVENT={id_event})", "id_event" to idEvent)
            }
        }catch (e: SQLiteConstraintException){

        }
    }

    private fun favState(){
        database.use{
            val result = select(FavouriteMatch.TABLE_FAVOURITE).whereArgs("(ID_EVENT = {id_event})","id_event" to idEvent)
            val favouriteMatch = result.parseList(classParser<FavouriteMatch>())
            if (!favouriteMatch.isEmpty()){
                isFav=true
            }
        }
    }
}
