package com.example.geoquiz


import androidx.lifecycle.ViewModel
import android.util.Log


class QuizViewModel: ViewModel(){

    private val QuestionL= listOf(
        Question(R.string.Q1,false),
        Question(R.string.Q2,true),
        Question(R.string.Q3,false)
        )
    var currentIndex=0
    var Cheater=true

    val currentAtheQ : Boolean
    get() = QuestionL[currentIndex].Answer
    val currentText :Int
    get() = QuestionL[currentIndex].textResId



    fun nextQ(){
        currentIndex =if (currentIndex< QuestionL.size)
        currentIndex+1 else currentIndex
    }
    fun backQ(){
    currentIndex =if (currentIndex> QuestionL.size)
        currentIndex-1 else currentIndex
}

}





