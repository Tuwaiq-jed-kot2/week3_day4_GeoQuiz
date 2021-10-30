package com.example.firstapplicationreplaning

import androidx.lifecycle.ViewModel
import com.example.geoquiz.R
import java.lang.reflect.Array.get

class QuizViewModel: ViewModel() {



    private val quistionBanc= listOf(
        Quistion(R.string.Q1,true,"written by rwan"),
        Quistion(R.string.Q2,true,"written by rwan"),
        Quistion(R.string.Q3,false,"written by rwan")
    )

    var currentIndex=0
    val isCheater=false


    val currentQuestion:Int
        get()=quistionBanc[currentIndex].questionResId

    val currentAnswer:Boolean
        get()=quistionBanc[currentIndex].answer


    val instructor:String
        get()=quistionBanc[currentIndex].instructor



    fun nextQuestion(){
        currentIndex=if(currentIndex<quistionBanc.size)currentIndex++ else currentIndex
    }
    fun previuosQuestion(){
        currentIndex=if (currentIndex<quistionBanc.size)currentIndex-- else currentIndex
    }

}