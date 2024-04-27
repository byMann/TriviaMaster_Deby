package com.ubayadev.triviamaster

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubayadev.triviamaster.databinding.PlayerItemBinding

class PlayerAdapter() : RecyclerView.Adapter<PlayerAdapter.ScoreViewHolder>()  {
    class ScoreViewHolder(val binding: PlayerItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {//func yang dipanggil untuk nge-load layout yang akan dipakai oleh recycler view
        val binding = PlayerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScoreViewHolder(binding)
    }

    override fun getItemCount(): Int {//function yang menghasilkan jumlah data yang mau ditampilkan di recyclerView:jumlah isi arrayList
        return 10
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val sortedPlayers = Global.player.sortedByDescending { it.score }

        with(holder.binding) {
            txtPlayersName.text = sortedPlayers[position].name
            txtCategory.text = sortedPlayers[position].category
            txtLevel.text = sortedPlayers[position].level
            txtFinalScore.text = sortedPlayers[position].score.toString()
            if(sortedPlayers[position].pict == "tiger"){
                imgPlayer.setImageResource(R.drawable.tiger)
            }
            else if(sortedPlayers[position].pict == "rabbit"){
                imgPlayer.setImageResource(R.drawable.rabbit)
            }
            else if(sortedPlayers[position].pict == "koala"){
                imgPlayer.setImageResource(R.drawable.koala)
            }
        }
    }

}