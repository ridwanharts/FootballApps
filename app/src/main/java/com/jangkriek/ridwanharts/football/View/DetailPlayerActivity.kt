package com.jangkriek.ridwanharts.football.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.jangkriek.ridwanharts.football.Model.Player
import com.jangkriek.ridwanharts.football.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayerActivity : AppCompatActivity() {

    private lateinit var player: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

        player = intent.getParcelableExtra("player_data")

        tv_player_detail.text = player.strPlayer
        tv_player_weight.text = player.strWeight
        tv_player_height.text = player.strHeight
        tv_player_pos.text = player.strPosition
        tv_player_desc.text = player.strDescription
        Picasso.get().load(player.strCutout).into(iv_player_detail)
        Picasso.get().load(player.strFanart1).into(iv_banner)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = player.strPlayer

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
