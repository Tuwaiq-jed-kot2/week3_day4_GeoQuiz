package com.example.geoquiz

import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    private val questionsBank = listOf(
        Questions(R.string.first_question,false,false,"anas"),
        Questions(R.string.second_question,true,false,"Humaid"),
        Questions(R.string.third_question,false,false,"Humaid"),
        Questions(R.string.fourth_question,true,false,"Humaid"),
        Questions(R.string.fifth_question,true,false,"Humaid"),
        Questions(R.string.sixth_question,true,false,"Humaid")

    )

    var currentIndex = 0

    var cheatingCount = 3

    var currentQuestion : Int
            get() =questionsBank[currentIndex].stringResId
            set(value) {
                questionsBank[currentIndex].stringResId = value
            }
    val currentQuestionAnswer : Boolean
            get() = questionsBank[currentIndex].answer
    var currentQuestionIsAnswered :Boolean
            get() = questionsBank[currentIndex].isAnswered
            set(value) {
                questionsBank[currentIndex].isAnswered = value
            }

    val currentQuestionWriter : String
            get() = questionsBank[currentIndex].writtenBy




    fun nextQuestion (){
        if (currentIndex in 0..questionsBank.size -2 ){
            currentIndex = (currentIndex + 1)


        }
    }
    fun previousQuestion () {
        if (currentIndex > 0){
            currentIndex = (currentIndex - 1)

        }
    }

}