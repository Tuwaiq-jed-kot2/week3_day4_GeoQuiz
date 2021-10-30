package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel


private const val TAG = "MainQuizViewModel"

class Quiz_viewModel : ViewModel() {

    var currentIndex =0

    var isCheater = false

    val currentQuestionText :Int
        get()=questionBank[currentIndex].textResId

    val currentQustionAnwser :Boolean
                            get() = questionBank[currentIndex].answer

    val currentQustionCheckAn :Boolean
        get() = questionBank[currentIndex].isSolved



    val qustionBankSize :Int
        get()=questionBank.size


//    var IsSolve :Int
//        set(isSolved) = questionBank[currentIndex].isSolved

//    init {
//        Log.d(TAG,"hi there i'm from the viewer model class")
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        Log.d(TAG,"\$_$ you cleared ")
//    }

    private val questionBank = listOf(
        Qusetion(R.string.first_question, true,false),
        Qusetion(R.string.second_question, false, false),
        Qusetion(R.string.third_question,false,false)
    )


 fun nextQustion(){
    currentIndex = (currentIndex + 1) % questionBank.size
}

    fun pervQustion(){
        currentIndex = (currentIndex - 1) % questionBank.size
    }

}