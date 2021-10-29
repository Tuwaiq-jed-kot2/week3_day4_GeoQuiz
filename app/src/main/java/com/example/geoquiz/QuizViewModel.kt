package com.example.geoquiz


import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel(){
    var currentIndex = 0

    private val questionBank = listOf(
        Question(R.string.first_q,true),
        Question(R.string.second_q,true),
        Question(R.string.third_q,false)

    )


    var isCheater = false

    val currentQ : Int
        get() = questionBank[currentIndex].questionResId

    val currentAns : Boolean
        get() = questionBank[currentIndex].answer


    fun nextQuestion(){
        currentIndex = (++currentIndex) % questionBank.size
    }



}