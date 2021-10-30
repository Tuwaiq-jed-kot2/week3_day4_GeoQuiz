package com.example.geoquiz
import androidx.lifecycle.ViewModel

class QviewModel : ViewModel() {

    private var questionBank = listOf(
        Questions(R.string.q_1, true),
        Questions(R.string.q_2, false),
        Questions(R.string.q_3, false),
        Questions(R.string.q_4,true),
        Questions(R.string.q_5,false)
    )

    var currentIndex: Int = 0


    val currentQuestioText: Int
        get() = questionBank[currentIndex].textQuestions

    val currentQuestionsAnswer: Boolean
        get() = questionBank[currentIndex].answer

    var isCheater = false

    fun nextQuestion() {
        currentIndex = if (currentIndex < questionBank.size-1) ++currentIndex else currentIndex
        // currentIndex = (currentIndex + 1) % questionBank.size another way
    }

    fun prevQuestion() {
        currentIndex= if (currentIndex !=0) --currentIndex else currentIndex

    }


}