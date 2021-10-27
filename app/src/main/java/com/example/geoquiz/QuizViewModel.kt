package com.example.easyquize

import androidx.lifecycle.ViewModel
import com.example.geoquiz.Question
import com.example.geoquiz.R


class QuizViewModel : ViewModel() {

    private val questionBank = listOf(
        Question(R.string.Q1,true),
        Question(R.string.Q2,false),
        Question(R.string.Q3,false),
        Question(R.string.Q4,true)
    )
    var currentIndex = 0
    val currentQuestion:Int
        get() = questionBank[currentIndex].textResIdRes

    val currentAnswer:Boolean
        get() = questionBank[currentIndex].answer

    val isCheater = false

    fun nextQuestion(){
        currentIndex = if (currentIndex < questionBank.size-1 ) ++currentIndex else currentIndex
    }
}