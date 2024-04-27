package com.ubayadev.triviamaster

data class Question(
    val question: String,
    val answers: List<String>,
    val answerPercentages: List<Int>
)
