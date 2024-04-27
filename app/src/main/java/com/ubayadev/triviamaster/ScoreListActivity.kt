package com.ubayadev.triviamaster

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ubayadev.triviamaster.databinding.ActivityScoreListBinding

class ScoreListActivity : AppCompatActivity() {
    private lateinit var binding:ActivityScoreListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PlayerAdapter()

        val lm: LinearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = lm
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = PlayerAdapter()


        binding.btnHome.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        val alertDialogBuilder = AlertDialog.Builder(this)

        // Set pesan peringatan
        alertDialogBuilder.setMessage("Play Again?")

        // Tombol "Ya"
        alertDialogBuilder.setPositiveButton("Play") { dialog, which ->
            super.onBackPressed()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Tombol "Tidak"
        alertDialogBuilder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
            finishAffinity()
        }

        // Buat dan tampilkan dialog
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}