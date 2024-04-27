package com.ubayadev.triviamaster

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ubayadev.triviamaster.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding

    companion object{
        val SCORE = "key_score"
        val PLAYER_NAME = "key_player_name"
        val CATEGORIES = "key_categories"
        val LEVEL = "key_level"
        val IMG = "key_img"
    }
    var playerName = "Ani"
    var isHelped = false
    var score = 0
    var storeIndex=0
    var currentQuestion = 0

    var userAnswer = 0
    var correctAnswer = 0

    var selectedCategory = "History"
    var selectedLevel = "medium"
    var chosenQuestion: ArrayList<Question> = ArrayList()
    var selectedImg = "rabbit"

    fun storeChosenQuestions(selectedCategory: String, selectedLevel: String) {
        // Cari soal dengan kategori yang dipilih
        var category = Global.questionsBank.find { it.name == selectedCategory }
        if (category != null) {
            // Cari soal dengan level yang dipilih
            var questions = when (selectedLevel) {
                "Easy" -> category.difficultyLevels.Easy
                "Medium" -> category.difficultyLevels.Medium
                "Hard" -> category.difficultyLevels.Hard
                else -> emptyList()
            }

            // Simpan pertanyaan ke dalam ArrayList CHOSENQUESTION
            if (questions.isNotEmpty()) {
                for (questionToDisplay in questions) {
                    // Mengacak urutan jawaban
                    var shuffledAnswers = questionToDisplay.answers.shuffled()
                    var shuffledAnswerPercentages = mutableListOf<Int>()

                    // Memastikan urutan persentase sesuai dengan urutan jawaban yang sudah diacak
                    for (answer in shuffledAnswers) {
                        val index = questionToDisplay.answers.indexOf(answer)
                        shuffledAnswerPercentages.add(questionToDisplay.answerPercentages[index])
                    }

                    // Menyimpan jawaban yang sudah diacak ke dalam objek Question
                    chosenQuestion += Question(
                        questionToDisplay.question,
                        shuffledAnswers,
                        shuffledAnswerPercentages
                    )
                }
            }
        }
    }


    fun displayQuestions() {
        if (currentQuestion < chosenQuestion.size) { // pastikan indeks berada dalam rentang yang benar

            binding.txtQuestion.text = chosenQuestion[currentQuestion].question
            binding.btnAnswer0.text = chosenQuestion[currentQuestion].answers[0].toString()
            binding.btnAnswer1.text = chosenQuestion[currentQuestion].answers[1].toString()
            binding.btnAnswer2.text = chosenQuestion[currentQuestion].answers[2].toString()
            binding.btnAnswer3.text = chosenQuestion[currentQuestion].answers[3].toString()

        } else {
            Toast.makeText(this, "SOAL HABIS", Toast.LENGTH_SHORT).show()
        }
        resetButtons()
    }

    fun nextQuestion(){
        binding.txtJumlahPertanyaan.text = "${currentQuestion+1} / ${chosenQuestion.size}"
        binding.txtScore.text = "$score"

        binding.progressBar.progress += 20

        if (binding.progressBar.progress > 100) {
            binding.progressBar.progress = 100
        }

        if(currentQuestion<chosenQuestion.size){
            displayQuestions()
        }

        else{
            binding.txtQuestion.text = "FINISH"
            binding.btnAnswer0.isEnabled = false
            binding.btnAnswer1.isEnabled = false
            binding.btnAnswer2.isEnabled = false
            binding.btnAnswer3.isEnabled = false
        }
    }

    fun cariJawaban(){
        val maxPercentage = chosenQuestion[currentQuestion].answerPercentages.maxOrNull()

        if (maxPercentage != null) {
            // Mendapatkan indeks jawaban yang benar
            val correctAnswerIndex = chosenQuestion[currentQuestion].answerPercentages.indexOf(maxPercentage)

            // Jika indeks tombol jawaban cocok dengan indeks jawaban yang benar, maka tombol tersebut adalah jawaban yang benar
            if (binding.btnAnswer0.text.toString() == chosenQuestion[currentQuestion].answers[correctAnswerIndex]) {
                correctAnswer = 0
            } else if (binding.btnAnswer1.text.toString() == chosenQuestion[currentQuestion].answers[correctAnswerIndex]) {
                correctAnswer = 1
            } else if (binding.btnAnswer2.text.toString() == chosenQuestion[currentQuestion].answers[correctAnswerIndex]) {
                correctAnswer = 2
            } else if (binding.btnAnswer3.text.toString() == chosenQuestion[currentQuestion].answers[correctAnswerIndex]) {
                correctAnswer = 3
            }
        }
    }

    fun cekJawaban(){
        cariJawaban()
        if(userAnswer == correctAnswer){
            when (selectedLevel) {
                "Easy" -> score += 1000
                "Medium" -> score += 2000
                "Hard" -> score += 3000
            }
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()

            currentQuestion++
            if(currentQuestion<5){
                openResume()
                nextQuestion()

            }
            else{
                openScoreList()
            }
        }
        else if(userAnswer != correctAnswer){
            binding.txtQuestion.text = "You Lose!"
            binding.btnAnswer0.isEnabled = false
            binding.btnAnswer1.isEnabled = false
            binding.btnAnswer2.isEnabled = false
            binding.btnAnswer3.isEnabled = false

            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
            openScoreList()
        }
    }

    fun openScoreList(){
        val intent = Intent(this,ScoreListActivity::class.java)
        saveGameState()
        startActivity(intent)
    }

    fun openResume(){
        val intent = Intent(this, ResumeActivity::class.java)
        intent.putExtra(SCORE, score)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //ambil nilai dari sharedpref
        val sharedFile = "com.ubayadev.spongebobquiz"
        val shared: SharedPreferences = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        playerName = shared.getString(PLAYER_NAME, "")?:""
        binding.txtPlayerName.text = playerName
        binding.progressBar.progress = (currentQuestion+1)*20

        selectedCategory = shared.getString(CATEGORIES, "") ?: ""
        selectedLevel = shared.getString(LEVEL, "")?:""
        selectedImg = shared.getString(IMG, "")?:""
        //buat arrayList berisi soal untuk kategori dan level yang dipilih
        storeChosenQuestions(selectedCategory, selectedLevel)

        chosenQuestion.shuffle()
        chosenQuestion

        displayQuestions()

        binding.btnAnswer0.setOnClickListener {
            // Mendapatkan jawaban user
            userAnswer = 0
            cekJawaban()
        }
        binding.btnAnswer1.setOnClickListener {
            // Mendapatkan jawaban user
            userAnswer = 1
            cekJawaban()
        }
        binding.btnAnswer2.setOnClickListener {
            // Mendapatkan jawaban user
            userAnswer = 2
            cekJawaban()
        }
        binding.btnAnswer3.setOnClickListener {
            // Mendapatkan jawaban user
            userAnswer = 3
            cekJawaban()
        }

        binding.btnAsk.setOnClickListener {
            if(isHelped == false)
            {
                askAudience()
                binding.btnAsk.isEnabled = false
                binding.btnAsk.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.myColor))
                isHelped = true
            }
            else{
                Toast.makeText(this, "ONLY ONE CHANCE AT A TIME", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnHalf.setOnClickListener {
            if(isHelped == false)
            {
                fiftyFifty()
                binding.btnHalf.isEnabled = false
                binding.btnHalf.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.myColor))
                isHelped = true
            }
            else{
                Toast.makeText(this, "ONLY ONE CHANCE AT A TIME", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCall.setOnClickListener {
            if(isHelped == false)
            {
                phoneFriend(selectedLevel)

                binding.btnCall.isEnabled = false
                binding.btnCall.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.myColor))

                isHelped = true
            }
            else{
                Toast.makeText(this, "ONLY ONE CHANCE AT A TIME", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun askAudience(){
        cariJawaban()
        println("correct answer" + correctAnswer)
        binding.txtQuestion.text = "${chosenQuestion[currentQuestion].question}"
        binding.btnAnswer0.text = "${chosenQuestion[currentQuestion].answers[0]} (${chosenQuestion[currentQuestion].answerPercentages[0]}%)"
        binding.btnAnswer1.text = "${chosenQuestion[currentQuestion].answers[1]} (${chosenQuestion[currentQuestion].answerPercentages[1]}%)"
        binding.btnAnswer2.text = "${chosenQuestion[currentQuestion].answers[2]} (${chosenQuestion[currentQuestion].answerPercentages[2]}%)"
        binding.btnAnswer3.text = "${chosenQuestion[currentQuestion].answers[3]} (${chosenQuestion[currentQuestion].answerPercentages[3]}%)"
    }

    fun fiftyFifty() {
        cariJawaban()
        // Buat daftar indeks jawaban yang tersedia (0, 1, 2, 3)
        val availableIndexes = mutableListOf(0, 1, 2, 3)

        // Hapus indeks jawaban yang benar dari daftar
        availableIndexes.remove(correctAnswer)

        // Acak 1 indeks dari daftar sisa
        val randomIndex = availableIndexes.shuffled().firstOrNull()

        binding.btnAnswer0.visibility = View.INVISIBLE
        binding.btnAnswer1.visibility = View.INVISIBLE
        binding.btnAnswer2.visibility = View.INVISIBLE
        binding.btnAnswer3.visibility = View.INVISIBLE
        when(correctAnswer){
            0->{
                binding.btnAnswer0.visibility = View.VISIBLE
            }
            1->{
                binding.btnAnswer1.visibility = View.VISIBLE
            }
            2->{
                binding.btnAnswer2.visibility = View.VISIBLE
            }
            3->{
                binding.btnAnswer3.visibility = View.VISIBLE
            }
        }
        // Sembunyikan dua tombol jawaban yang dipilih secara acak
        when (randomIndex) {
            0 -> {
                binding.btnAnswer0.visibility = View.VISIBLE
            }
            1 -> {
                binding.btnAnswer1.visibility = View.VISIBLE
            }
            2 -> {
                binding.btnAnswer2.visibility = View.VISIBLE
            }
            else -> {
                binding.btnAnswer3.visibility = View.VISIBLE
            }
        }
    }


    fun resetButtons() {
        binding.btnAnswer0.visibility = View.VISIBLE
        binding.btnAnswer1.visibility = View.VISIBLE
        binding.btnAnswer2.visibility = View.VISIBLE
        binding.btnAnswer3.visibility = View.VISIBLE

        binding.btnAnswer0.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.PURPLE))
        binding.btnAnswer1.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.PURPLE))
        binding.btnAnswer2.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.PURPLE))
        binding.btnAnswer3.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.PURPLE))

        isHelped = false
    }
    fun phoneFriend(level: String) {
        val confidenceLevel: Int = when (level.toLowerCase()) {
            "easy" -> 100   // 100% confidence
            "medium" -> 85 // 85% confidence
            "hard" -> 70   // 70% confidence
            else -> 10      // Default to 100% confidence for unknown levels
        }

        var markTrue = -1

        // Find the answer with the closest percentage to the random value
        var closestDifference = Int.MAX_VALUE // Inisialisasi dengan nilai yang sangat besar
        for (i in 0..3) {
            val difference = (Math.abs(chosenQuestion[currentQuestion].answerPercentages[i] - confidenceLevel)).toInt()
            if (difference < closestDifference) {
                closestDifference = difference
                markTrue = i
            }

        }

        // Mengubah warna latar belakang jawaban yang dipilih menjadi hijau muda
        when (markTrue) {
            0 -> binding.btnAnswer0.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.lightGreen))
            1 -> binding.btnAnswer1.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.lightGreen))
            2 -> binding.btnAnswer2.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.lightGreen))
            3 -> binding.btnAnswer3.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.lightGreen))
        }
    }

    override fun onPause() {
        super.onPause()
    }

    private  fun updateScore(){
        val sharedFile = "com.ubayadev.spongebobquiz"
        val shared: SharedPreferences = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        var editor:SharedPreferences.Editor = shared.edit()
        editor.putInt(SCORE, score)
        editor.apply()
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
        updateScore()
    }
    override fun onBackPressed() {
        val alertDialogBuilder = AlertDialog.Builder(this)

        // Set pesan peringatan
        alertDialogBuilder.setMessage("Restart the game?")

        // Tombol "Ya"
        alertDialogBuilder.setPositiveButton("Yes") { dialog, which ->
            super.onBackPressed()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
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