package com.example.geoquiz

import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
class QuizViewModel: ViewModel() {

    //Attributes
    private val questionBank = listOf(
        Question(R.string.firstQuestion, false, false),
        Question(R.string.secondQuestion, false, false),
        Question(R.string.thirdQuestion, true, false),
        Question(R.string.fourthQuestion, true, false),
        Question(R.string.fifthQuestion, true, false),
        Question(R.string.sixthQuestion, true, false),
        Question(R.string.seventhQuestion, false, false),
        Question(R.string.eighthQuestion, true, false),
        Question(R.string.ninthQuestion, false, false),
        Question(R.string.tenthQuestion, true, false)
    )

    var currentIndex = 0
    var score = 0
    var isCheater = false
    var cheatCount = 0

    val currentQuestionText: Int
            get() = questionBank[currentIndex].textResId
    val currentQuestionAnswer: Boolean
            get() = questionBank[currentIndex].answer
    var isCurrentQuestionAnswered: Boolean
            get() = questionBank[currentIndex].isAnswered
            set(value) {
                questionBank[currentIndex].isAnswered = value
            }
    val questionsSize: Int
            get() = questionBank.size

    //Functions
    fun nextQuestion(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun previousQuestion(){
        if (currentIndex > 0){
            currentIndex--
        }else{
            currentIndex = 0
        }
    }
}