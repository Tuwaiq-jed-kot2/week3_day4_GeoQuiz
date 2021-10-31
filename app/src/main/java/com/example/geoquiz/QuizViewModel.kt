package com.example.geoquiz

import androidx.lifecycle.ViewModel

class QuizViewModel:ViewModel() {

    private var currentIndex= 0

    var isCheater =false

    private val questionBank = listOf(
        Question(R.string.Q1,true),
        Question(R.string.Q2,false),
        Question(R.string.Q3,true),
        Question(R.string.Q4,false),
        Question(R.string.Q5,false)

    )


    val currentQuestion:Int
        get() = questionBank[currentIndex].questionResId

    val currentAnswer:Boolean
    get() = questionBank[currentIndex].answer


    fun nextQuestion(){
        currentIndex = if (currentIndex < questionBank.size-1 ) ++currentIndex else currentIndex
    }

    fun previousQuestion(){
        currentIndex = if (currentIndex < questionBank.size+1 ) --currentIndex else currentIndex
    }






}

