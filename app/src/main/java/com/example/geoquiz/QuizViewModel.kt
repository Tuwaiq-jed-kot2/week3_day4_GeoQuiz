package com.example.geoquiz


import androidx.lifecycle.ViewModel
import android.util.Log

private const val TAg ="quizViewModel"
class QuizViewModel: ViewModel() {

    private val questionBank = listOf(
        Question(R.string.first_question, false),
        Question(R.string.second_question, false),
        Question(R.string.third_question, true),
        Question(R.string.forth_question, true),
        Question(R.string.fifth_question, false)
    )
    val currentQuestionAnswer: Boolean
        get() = questionBank[currevtIndex].answer

    val currntQuestionText: Int
        get() = questionBank[currevtIndex].textResId

    var currevtIndex = 0
    var isCheater = true


    fun nextQ() {
        currevtIndex = if (currevtIndex < questionBank.size)
            currevtIndex + 1 else currevtIndex
    }

    fun backQ() {
        currevtIndex = if (currevtIndex > questionBank.size)
            currevtIndex - 1 else currevtIndex
    }

}




