package com.example.geoquiz
import androidx.lifecycle.ViewModel



class QuizViewModel:ViewModel() {

      val questionBank = listOf(
        Question(R.string.first_question, answer = true, isSolved = false, isCheated = false),
        Question(R.string.second_question, answer = false, isSolved = false, isCheated = false),
        Question(R.string.third_question, answer = false, isSolved = false, isCheated = false),
        Question(R.string.fourth_question, answer = true, isSolved = false, isCheated = false),
        Question(R.string.fifth_question, answer = false, isSolved = false, isCheated = false),
        Question(R.string.sixth_question, answer = true, isSolved = false, isCheated = false),
        Question(R.string.seventh_question, answer = true, isSolved = false, isCheated = false),
        Question(R.string.eighth_question, answer = false, isSolved = false, isCheated = false),
        Question(R.string.nine_question, answer = true, isSolved = false, isCheated = false),
        Question(R.string.tenth_question, answer = false, isSolved = false, isCheated = false)
    )

    var currentIndex = 0

    var currentScore = 0

    val currentQuestionText get() = questionBank[currentIndex].textResId

    val currentQuestionAnswer get() = questionBank[currentIndex].answer


    var cheatingCounter = 3

    fun nextQuestion(){
            currentIndex = (currentIndex+1)%questionBank.size
    }

    fun backQuestion(){
        currentIndex = if (currentIndex == 0) {
            questionBank.lastIndex
        } else
            (currentIndex - 1)
    }

    fun tryAgain(){
        var i = 0
        while (i < questionBank.size){
            questionBank[i].isSolved = false
            i++
        }

        currentIndex = 0
        currentScore = 0
    }

    fun correctScore(){
        questionBank[currentIndex].isSolved = true
        currentScore += 1
    }

    fun wrongScore(){
        questionBank[currentIndex].isSolved = true
        currentScore += -1
    }

}