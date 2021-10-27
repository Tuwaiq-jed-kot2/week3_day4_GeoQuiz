package com.example.easyquize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.geoquiz.R


class MainActivity : AppCompatActivity() {

    private lateinit var trueBtn: Button
    private lateinit var falseBtn: Button
    private lateinit var nextBtn: Button
    private lateinit var cheatBtn: Button
    private lateinit var questionTV: TextView


    private val quizViewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init_view()

        listeners()

        updateQuestion()
    }

    private fun listeners() {
        trueBtn.setOnClickListener {
            checkAnswer(true)
        }

        falseBtn.setOnClickListener {
            checkAnswer(false)
        }

        nextBtn.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }

        cheatBtn.setOnClickListener {
            val intent = Intent(this, CheatActivity::class.java)
            startActivity(intent)
        }


    }

    private fun updateQuestion() {
        val questionId = quizViewModel.currentQuestion
        questionTV.setText(questionId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentAnswer

        val theMsg = when (correctAnswer) {
            userAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }

        Toast.makeText(this, theMsg, Toast.LENGTH_LONG).show()

    }

    private fun init_view() {
        trueBtn = findViewById(R.id.true_btn)
        falseBtn = findViewById(R.id.false_btn)
        nextBtn = findViewById(R.id.next_btn)
        cheatBtn = findViewById(R.id.cheat_btn)
        questionTV = findViewById(R.id.question_tv)

    }
}



