package com.example.geoquiz

import androidx.lifecycle.ViewModel
/*------------------------------------------------------------------------------*/
class QuizViewModel : ViewModel() {
    private val questionBank = listOf(
        Question(R.string.Q1 , true),
        Question(R.string.Q2 , true),
        Question(R.string.Q3 , false)
    )
    /*------------------------------------------------------------------------------*/
    var currentIndex = 0
    /*------------------------------------------------------------------------------*/
    fun NextQuestion() {
        currentIndex =if (currentIndex < questionBank.size -1) ++currentIndex else currentIndex
    }
    /*------------------------------------------------------------------------------*/
    fun PreQuestion(){
        if ( currentIndex > 0 ) {
            currentIndex = (currentIndex -1) % questionBank.size
        } else if (currentIndex == 0 ){
            currentIndex = 0
        }
    }
    /*------------------------------------------------------------------------------*/
    val currentQuestion:Int
            get() = questionBank[currentIndex].textResId
    /*------------------------------------------------------------------------------*/
    val currentAnswer:Boolean
            get() = questionBank[currentIndex].answer
    /*------------------------------------------------------------------------------*/
    var isCheater = false
}