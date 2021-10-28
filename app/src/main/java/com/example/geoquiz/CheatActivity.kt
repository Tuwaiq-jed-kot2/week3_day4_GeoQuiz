package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider



const val EXTRA_ANSWER_SHOWN = "CHEATER ALERT!!"

class CheatActivity : AppCompatActivity() {

    private lateinit var showQuestion :TextView
    private lateinit var showAnswerTv:TextView
    private lateinit var showAnswerButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        showAnswerButton = findViewById(R.id.show_answer_button)
        showAnswerTv = findViewById(R.id.show_answer_Tv)
        showQuestion = findViewById(R.id.show_question)


        var cheatAnswer = false

        cheatAnswer = intent.getBooleanExtra(EXTRA_SHOW_ANSWER,false)

        var cheatQuestion = 0

        cheatQuestion = intent.getIntExtra(EXTRA_SHOW_QUESTION,0)


        showAnswerButton.setOnClickListener {
            showQuestion.setText(cheatQuestion)
            showAnswerTv.text = cheatAnswer.toString()
            setAnswerShownResult()
        }


    }

    private fun setAnswerShownResult(){
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN,true)
        }

        setResult(Activity.RESULT_OK,data)
    }
}