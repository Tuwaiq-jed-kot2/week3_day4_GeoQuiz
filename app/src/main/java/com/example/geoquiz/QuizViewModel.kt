package com.example.geoquiz

import androidx.lifecycle.ViewModel


private const val Tag="QuizViewModel"
class QuizViewModel:ViewModel() {

    private val questionBank = listOf(

        Question(R.string.First_Question, false),
        Question (R.string.second_question, false),
        Question(R.string.third_question,true),
        Question(R.string.forth_question,true)
    )
     var currentIndex = 0

    val currentQuestionText:Int
                 get()=questionBank[currentIndex].textResId
    val currentQuestionAnswer: Boolean
                 get()=questionBank[currentIndex].answer

    var isCheater = false

    fun nextQuestion(){
        currentIndex=(currentIndex+1 )% questionBank.size
    }

    fun backQuestion(){

        currentIndex=(currentIndex-1 )% questionBank.size



    }

}



