package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG="QuizViewModel"

class QuizViewModel:ViewModel() {

    private val questionBank= listOf(
        Question(R.string.first_question,true,"Ali"),
        Question(R.string.second_question,false,"Ahmed"),
        Question(R.string.thrid_question,false,"Roya "),

        )

    var currentIndex=0
    var isCheater=false

     val cunrrentQuestionText:Int
                        get() = questionBank[currentIndex].textResId
    val cunrrentQuestionAnswer:Boolean
        get() = questionBank[currentIndex].answer

    val qestionConstrctor:String
        get() = questionBank[currentIndex].constractor

      fun nextQuestion(){
          currentIndex=(currentIndex+1)%questionBank.size
      }

    init {
                Log.d(TAG,"hi is from the view model class")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG,"\$_$ you clear mess")
    }

}