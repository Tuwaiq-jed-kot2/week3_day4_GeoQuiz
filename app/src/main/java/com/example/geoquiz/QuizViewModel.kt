package com.example.geoquiz
import androidx.lifecycle.ViewModel

class QuizViewModel: ViewModel() {

    val questionBank = listOf(
        Question(R.string.question_1, true, false, "Rajwa", false, "Anas"),

        Question(R.string.question_2, false, false, "Sara", false, "..."),

        Question(R.string.question_3, true, false, "Kendah", false, "I created this")
    )

    var currentIndex = 0

    var currentQuestionText: Int = 0
        get() = questionBank[currentIndex].textResId

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val questionInstructor: String
        get() = "Instructor name: " + questionBank[currentIndex].instructor

    val currentHint: String
            get() = questionBank[currentIndex].hint

    var cheatCount = 0

    fun nextQuestion(): Int {
        if (currentIndex < questionBank.size - 1) {
            currentIndex++
            return currentQuestionText
        }
        return currentQuestionText
    }

    fun prevQuestion(): Int {
        if (currentIndex != 0) {
            currentIndex--
            return currentQuestionText
        }
        return currentQuestionText
    }
}