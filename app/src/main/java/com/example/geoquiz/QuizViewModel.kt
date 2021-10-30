package com.example.geoquiz

import androidx.lifecycle.ViewModel

class QuizViewModel:ViewModel() {

    private val questionBank = listOf(
        Question(R.string.Q1,true),
        Question(R.string.Q2,false),
        Question(R.string.Q3,true),
        Question(R.string.Q4,false)

    )


    var currentIndex = 0


    val currentQuestion:Int
                get() = questionBank[currentIndex].questionResId

    val currentAnswer:Boolean
                get() = questionBank[currentIndex].answer

    var isCheater = false


    fun nextQuestion (){
        // single line if condition
        currentIndex = if (currentIndex < questionBank.size-1 ) ++currentIndex else currentIndex
    }

}