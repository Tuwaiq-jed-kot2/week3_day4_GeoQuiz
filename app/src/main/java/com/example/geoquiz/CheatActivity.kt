package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class CheatActivity : AppCompatActivity() {

    private lateinit var answerTV : TextView
    private lateinit var showQuestionsTV: TextView
    private lateinit var showAnswerBtn: Button

    var rightAnswer = false
    var theQuestion = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        initialize()

        intentItems()

        showAnswerBtn.setOnClickListener {

            showQuestionsTV.setText(theQuestion)

            answerTV.setText(rightAnswer.toString())
            answerResult()


        }

    }

    private fun intentItems() {
        rightAnswer = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE_OR_NOT, false)
        theQuestion = intent.getIntExtra(EXTRA_QUESTION, 0)
    }

    private fun initialize() {
        answerTV = findViewById(R.id.answer_tv)
        showQuestionsTV = findViewById(R.id.show_q_tv)
        showAnswerBtn = findViewById(R.id.show_answer_btn)
    }

    private fun answerResult(){
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_IS_TRUE_OR_NOT,true)
        }
        setResult(Activity.RESULT_OK,data)

    }


}