package com.jangkriek.ridwanharts.football.Adapter

import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jangkriek.ridwanharts.football.Model.Player
import com.jangkriek.ridwanharts.football.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_player.view.*

class PlayerAdapter(private val player: List<Player>,
                    private val listener: (Player)->Unit): RecyclerView.Adapter<PlayerAdapter.ViewHolderPlayer>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolderPlayer {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_player,p0,false)
        return ViewHolderPlayer(view)
    }

    override fun getItemCount(): Int = player.size

    override fun onBindViewHolder(p0: ViewHolderPlayer, p1: Int) {
        p0.bindItem(player[p1], listener)
    }

    class ViewHolderPlayer(val view: View): RecyclerView.ViewHolder(view){

        fun bindItem(player: Player, listener: (Player) -> Unit){
            itemView.tv_player_name.text = player.strPlayer
            itemView.tv_player_pos.text = player.strPosition
            if (player.strCutout.isNullOrEmpty()){
                itemView.img_player.setImageResource(R.drawable.ic_teams)
            }else{
                Picasso.get().load(player.strCutout).into(itemView.img_player)
            }

            itemView.setOnClickListener {
                listener(player)
            }
        }
    }


}