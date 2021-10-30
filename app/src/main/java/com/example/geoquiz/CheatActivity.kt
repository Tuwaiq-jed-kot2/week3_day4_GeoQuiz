package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


const val EXTRA_ANSWER_SHOWN = "you are cheater!!!"

class CheatActivity : AppCompatActivity() {

    private lateinit var showAnswerBTN:Button
    private lateinit var answerTV:TextView
    private lateinit var questionTV:TextView


    var answerCheat = false
    var questionCheat = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        showAnswerBTN = findViewById(R.id.show_answer_btn)
        answerTV = findViewById(R.id.cheat_answer_tv)
        questionTV = findViewById(R.id.cheat_question_tv)

        answerCheat = intent.getBooleanExtra(KEY_ANSWER,true)
        questionCheat = intent.getIntExtra(KEY_QUESTION,0)

        showAnswerBTN.setOnClickListener {
            answerTV.setText(answerCheat.toString())
            questionTV.setText(questionCheat)
            setAnswerShownResult()
        }
    }

       fun setAnswerShownResult(){

        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN,true)
        }
        setResult(Activity.RESULT_OK,data)
    }

}