package com.ubayadev.triviamaster

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.ubayadev.triviamaster.databinding.ActivityMainBinding
import com.ubayadev.triviamaster.databinding.ActivityQuizBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        val PLAYER_NAME = "key_player_name"
        val CATEGORIES = "key_categories"
        val LEVEL = "key_level"
        val IMG = "key_img"
    }

    var img = ""
    var selectedCategory: String = ""
    var selectedLevel:String= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)


        binding.groupCategory.setOnCheckedChangeListener { radioGroup, i ->
            binding.radButtonHistory.setTextColor(Color.BLACK)
            binding.radButtonArt.setTextColor(Color.BLACK)
            binding.radButtonScience.setTextColor(Color.BLACK)

            var selectedRadio =  findViewById<RadioButton>(i)
            selectedRadio.setTextColor(Color.MAGENTA)
            selectedCategory = selectedRadio.text.toString()
            Toast.makeText(this, selectedCategory, Toast.LENGTH_SHORT).show()
        }

        binding.groupLevel.setOnCheckedChangeListener { radioGroup, i ->
            binding.rdButtonEasy.setTextColor(Color.BLACK)
            binding.rdButtonHard.setTextColor(Color.BLACK)
            binding.rdButtonMedium.setTextColor(Color.BLACK)

            var selectedRadio =  findViewById<RadioButton>(i)
            selectedRadio.setTextColor(Color.MAGENTA)
            selectedLevel = selectedRadio.text.toString()
            Toast.makeText(this, selectedLevel, Toast.LENGTH_SHORT).show()
        }

        binding.imgKoala.setOnClickListener {
            img="koala"
            val scaleX = ObjectAnimator.ofFloat(it, "scaleX", 1.0f, 1.2f, 1.0f)
            val scaleY = ObjectAnimator.ofFloat(it, "scaleY", 1.0f, 1.2f, 1.0f)

            scaleX.duration = 350
            scaleY.duration = 350

            val animatorSet = AnimatorSet()
            animatorSet.playTogether(scaleX, scaleY) // Menjalankan animasi secara bersamaan
            animatorSet.start()
        }

        binding.imgRabbit.setOnClickListener {
            img="rabbit"
            val scaleX = ObjectAnimator.ofFloat(it, "scaleX", 1.0f, 1.2f, 1.0f)
            val scaleY = ObjectAnimator.ofFloat(it, "scaleY", 1.0f, 1.2f, 1.0f)

            scaleX.duration = 350
            scaleY.duration = 350

            val animatorSet = AnimatorSet()
            animatorSet.playTogether(scaleX, scaleY) // Menjalankan animasi secara bersamaan
            animatorSet.start()
        }
        binding.imgTiger.setOnClickListener {
            img="tiger"
            val scaleX = ObjectAnimator.ofFloat(it, "scaleX", 1.0f, 1.2f, 1.0f)
            val scaleY = ObjectAnimator.ofFloat(it, "scaleY", 1.0f, 1.2f, 1.0f)

            scaleX.duration = 350
            scaleY.duration = 350

            val animatorSet = AnimatorSet()
            animatorSet.playTogether(scaleX, scaleY) // Menjalankan animasi secara bersamaan
            animatorSet.start()
        }

        binding.btnSubmit.setOnClickListener {
            if (binding.txtPlayersName.text.toString().isEmpty()) {
                // Tampilkan pesan kesalahan bahwa nama harus diisi
                Toast.makeText(this, "Nama harus diisi!", Toast.LENGTH_SHORT).show()
            }
            else if(selectedCategory.isEmpty()){
                Toast.makeText(this, "Kategori belum dipilih!", Toast.LENGTH_SHORT).show()
            }
            else if(selectedLevel.isEmpty()){
                Toast.makeText(this, "Level belum dipilih!", Toast.LENGTH_SHORT).show()
            }
            else if(img.isEmpty()){
                Toast.makeText(this, "Avatar belum dipilih!", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, img, Toast.LENGTH_SHORT).show()

                val intent = Intent(this, QuizActivity::class.java)
                val sharedFile = "com.ubayadev.spongebobquiz"
                val shared: SharedPreferences = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

                var editor: SharedPreferences.Editor = shared.edit()
                editor.putString(PLAYER_NAME, binding.txtPlayersName.text.toString())
                editor.putString(CATEGORIES, selectedCategory)
                editor.putString(LEVEL, selectedLevel)
                editor.putString(IMG, img)
                editor.apply()
                startActivity(intent)

                //mendestroy aktivitas sebelumnya
                finish()
            }
        }
    }
    override fun onBackPressed() {
        val alertDialogBuilder = AlertDialog.Builder(this)

        // Set pesan peringatan
        alertDialogBuilder.setMessage("Exit the game?")

        // Tombol "Ya"
        alertDialogBuilder.setPositiveButton("Exit") { dialog, which ->
            super.onBackPressed()
            finishAffinity()
        }

        // Tombol "Tidak"
        alertDialogBuilder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }

        // Buat dan tampilkan dialog
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}