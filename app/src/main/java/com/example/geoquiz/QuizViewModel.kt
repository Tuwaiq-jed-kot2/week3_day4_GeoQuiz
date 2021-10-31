package com.example.geoquiz

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.ViewModel

private const val Tag = "QuizViewModel"
class QuizViewModel:ViewModel() {



     val questionBank = listOf(
        Question(R.string.first_question,true,"shahad"),
        Question(R.string.second_question,false,"anas"),
        Question(R.string.third_question,false,"ahmed")
    )
        var currentIndex = 0
    val currentQuestion = 0
        val currentQuestionText : Int
                get()=questionBank[currentIndex].textResId
        val currentuestionAnswer : Boolean
                get() = questionBank[currentIndex].answer
            var isCheater = false

        val instructorName :String
        get()=questionBank[currentIndex].instructor

        fun nextQuestion(){
            currentIndex = (currentIndex+1) % questionBank .size

        }



}