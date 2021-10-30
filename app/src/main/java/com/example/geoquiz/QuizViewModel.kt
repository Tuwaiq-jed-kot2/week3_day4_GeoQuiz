package com.example.geoquiz

import android.widget.TextView
import androidx.lifecycle.ViewModel

class QuizViewModel: ViewModel() {

    private val questionBank = listOf(
        Question(R.string.first_question,true),
        Question(R.string.second_question,false),
        Question(R.string.third_question,true),
        Question(R.string.fourth_question,false)
    )

    var currentIndex = 0
    var isCheater = false

    val currentQuestion:Int
                get() = questionBank[currentIndex].questionText

    val currentAnswer: Boolean
                get() = questionBank[currentIndex].answer

    fun nextQues(){
        currentIndex = if (currentIndex < questionBank.size -1) currentIndex +1 else currentIndex
    }

    fun previousQues(){
        currentIndex = if (currentIndex == 0) currentIndex  else currentIndex -1
    }


}