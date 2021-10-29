package com.example.geoquiz


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

private const val KEY_INDEX: String = "INDEX"
const val Extra_ANSWER: String = "MainActivity"
const val Extra_QUESTION: String = "hh"


class MainActivity : AppCompatActivity() {
    private lateinit var trueBtn : Button
    private lateinit var falseBtn : Button
    private lateinit var questionText : TextView
    private lateinit var nextBtn : Button
    private lateinit var cheatBtn : Button

    private val QuizViewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX)?: 0
        QuizViewModel.currentIndex = currentIndex

        trueBtn = findViewById(R.id.true_btn)
        falseBtn = findViewById(R.id.false_btn)
        questionText = findViewById(R.id.question_text)
        nextBtn = findViewById(R.id.next_btn)
        cheatBtn = findViewById(R.id.cheat_btn)


        trueBtn.setOnClickListener {
            checkAnswer(true)
        }

        falseBtn.setOnClickListener {
            checkAnswer(false)
        }

        nextBtn.setOnClickListener {
            QuizViewModel.nextQuestion()
            updateQuestion()
        }

        cheatBtn.setOnClickListener {
            val intent = Intent(this,CheatActivity::class.java)
            intent.putExtra(Extra_ANSWER,QuizViewModel.currentAns)
            intent.putExtra(Extra_QUESTION,QuizViewModel.currentQ)

            startActivity(intent)
        }

        updateQuestion()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, QuizViewModel.currentIndex)
    }


    private fun updateQuestion() {
        val questionTextResId = QuizViewModel.currentQ

        questionText.setText(questionTextResId)

    }

    private fun checkAnswer(userAnswer: Boolean) {

        val correctAnswer = QuizViewModel.currentAns
        val toastMeassage = when {
            QuizViewModel.isCheater -> R.string.cheater_toast
            correctAnswer == userAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }
        Toast.makeText(this, toastMeassage, Toast.LENGTH_SHORT).show()

    }

}