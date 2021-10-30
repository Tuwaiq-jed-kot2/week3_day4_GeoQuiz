package com.example.newgeoquiz


import android.widget.Toast
import androidx.activity.R
import androidx.lifecycle.ViewModel

class QuizViewModel:ViewModel() {
     val questionBank= listOf(
         Question(R.string.Q1,false),
         Question(R.string.Q2,false),
         Question(R.string.Q3,true),
         Question(R.string.Q4,false),
         Question(R.string.Q5,true),
         Question(R.string.Q6,true),



     )

    var currentIndex =0

    val currentQuestion:Int
                get() = questionBank[currentIndex].questionResId

     val currentAnswer:Boolean
                  get() = questionBank[currentIndex].answer

    var isCheater =false


    fun nextQuestion(){

            currentIndex = (currentIndex +1) % questionBank.size


    }
    fun preQuestion(){
        currentIndex= if (currentIndex < questionBank.size-1) --currentIndex else currentIndex

    }




}