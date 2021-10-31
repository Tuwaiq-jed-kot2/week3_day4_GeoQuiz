package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

private const val KEY_INDEX = "index"
const val EXTRA_QUESTION = "MainActivity_Question"
const val EXSTRA_ANSWER="MainActivity.answerIsTrue"

private const val Requst_Code_Cheat = 999

class MainActivity : AppCompatActivity() {
    private lateinit var falseButton: Button
    private lateinit var trueButton: Button
    private lateinit var nextButton: Button
    private lateinit var backButton: Button
    private lateinit var cheatButton: Button
    private lateinit var questionTextView: TextView

    val TAG = "Main_Activity"
    private val quizViewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java) }//(تم تعريف العنصر الوسيط بين البرفايدر والاوبجيكت الوسيط بين البرنامج والمودل)يجب ان يكون قلوبال ليتم استعاده من اي مكان
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == Requst_Code_Cheat) {
            quizViewModel.isCheating = data?.getBooleanExtra(EXSTRA_ANSWER_SHOW, false) ?: false
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentIndex = savedInstanceState?.getInt(KEY_INDEX)
            ?: 0
        quizViewModel.currentIndex = currentIndex

        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton = findViewById(R.id.next_question)
        backButton = findViewById(R.id.previous)
        cheatButton = findViewById(R.id.cheat_button)
        questionTextView = findViewById(R.id.question_Tv)

        cheatButton.setOnClickListener {
            val intent = Intent(this, CheatActivity2::class.java)
            intent.putExtra(EXSTRA_ANSWER, quizViewModel.currentQuestionAnswer)//
            intent.putExtra(EXTRA_QUESTION, quizViewModel.currentQuestionText)
            startActivityForResult(intent,Requst_Code_Cheat)//
        }
        falseButton.setOnClickListener {
            checkAnswer(userAnswer = false)
        }
        trueButton.setOnClickListener {
            checkAnswer(userAnswer = true)
        }
        nextButton.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }
        backButton.setOnClickListener {
            quizViewModel.backQuestion()
            updateQuestion()
        }

        questionTextView.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }
        updateQuestion()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
        outState.putInt(KEY_INDEX, quizViewModel.currentQuestionText)
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart())")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val toastMassege = when {
            quizViewModel.isCheating -> R.string.cheatM
            userAnswer == correctAnswer -> R.string.correct_toast
            else-> R.string.incorrect_tost
        }
        Toast.makeText(this, toastMassege, Toast.LENGTH_SHORT).show()
    }
}