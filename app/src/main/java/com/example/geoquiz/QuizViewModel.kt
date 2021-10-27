package com.example.easyquize

import androidx.lifecycle.ViewModel
import com.example.geoquiz.R


class QuizViewModel : ViewModel() {

    private val questionBnk = listOf(
        Question(R.string.Q1, true),
        Question(R.string.Q2, true),
        Question(R.string.Q3, true),
        Question(R.string.Q4, false)
    )
    var currentIndex = 0
    val currentQuestion: Int
        get() = questionBnk[currentIndex].questionResId

    val currentAnswer: Boolean
        get() = questionBnk[currentIndex].answer

    val isCheater = false

    fun nextQuestion() {
        currentIndex = if (currentIndex < questionBnk.size - 1) ++currentIndex else currentIndex
    }
}