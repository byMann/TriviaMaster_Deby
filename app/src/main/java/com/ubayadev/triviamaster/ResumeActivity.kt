package com.ubayadev.triviamaster

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.ubayadev.triviamaster.databinding.ActivityResumeBinding

class ResumeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResumeBinding

    companion object{
        val SCORE = "key_score"
        val PLAYER_NAME = "key_player_name"
        val CATEGORIES = "key_categories"
        val LEVEL = "key_level"
        val IMG = "key_img"
    }

    var playerName = ""
    var selectedCategory = ""
    var selectedLevel = ""
    var selectedImg = ""

    var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResumeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //ambil nilai dari sharedpref
        val sharedFile = "com.ubayadev.spongebobquiz"
        val shared: SharedPreferences = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        playerName = shared.getString(PLAYER_NAME, "")?:""
        selectedCategory = shared.getString(CATEGORIES, "") ?: ""
        selectedLevel = shared.getString(LEVEL, "")?:""
        selectedImg = shared.getString(IMG, "")?:""
        score = intent.getIntExtra(SCORE,0)

        binding.txtScoreTemp.text = score.toString()

        binding.btnResume.setOnClickListener {
            finish()
        }
        binding.btnSurrender.setOnClickListener {
            val intent = Intent(this,ScoreListActivity::class.java)
            saveGameState()
            startActivity(intent)
            finish()
        }
    }

    private fun saveGameState() {
        //val intent= Intent(this, ScoreListActivity::class.java)
        val player = Player(playerName, score, selectedCategory, selectedLevel, selectedImg)
        Global.player.add(player)

        for (player in Global.player) {
            // Akses properti dari setiap objek Player
            val playerName = player.name
            val score = player.score
            val category = player.category
            val level = player.level
            val image = player.pict

            // Lakukan sesuatu dengan data player yang dibaca
            println("Player: $playerName, Score: $score, Category: $category, Level: $level, Image: $image")
        }
        val sharedFile = "com.ubayadev.spongebobquiz"
        val shared: SharedPreferences = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        var editor:SharedPreferences.Editor = shared.edit()
        editor.putInt(QuizActivity.SCORE, score)
        editor.apply()
    }

    override fun onBackPressed() {
        val alertDialogBuilder = AlertDialog.Builder(this)

        // Set pesan peringatan
        alertDialogBuilder.setMessage("Continue the game?")

        // Tombol "Ya"
        alertDialogBuilder.setPositiveButton("Yes") { dialog, which ->
            super.onBackPressed()
            finish()
        }

        // Tombol "Tidak"
        alertDialogBuilder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
            val intent = Intent(this,ScoreListActivity::class.java)
            saveGameState()
            startActivity(intent)
            finish()
        }

        // Buat dan tampilkan dialog
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}