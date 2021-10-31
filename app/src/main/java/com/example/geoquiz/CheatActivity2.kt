package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
const val EXSTRA_ANSWER_SHOW = "wtf cheater"

class CheatActivity2 : AppCompatActivity() {

    private lateinit var answerTv:TextView
    private lateinit var showQuestion:TextView
    private lateinit var showAnswerBTN:Button

    var answerIsTrueOrFalse = false
    var questionCheat = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat2)

        answerTv = findViewById(R.id.answer_tv)
        showAnswerBTN = findViewById(R.id.show_Answer)
        showQuestion = findViewById(R.id.textView)


        answerIsTrueOrFalse = intent.getBooleanExtra(EXSTRA_ANSWER, false)
        questionCheat = intent.getIntExtra(EXTRA_QUESTION,0)



        showAnswerBTN.setOnClickListener {
            answerTv.setText(answerIsTrueOrFalse.toString())
            showQuestion.setText(questionCheat)
            setAnswerShowResult()
        }
    }
    fun setAnswerShowResult(){
        val data = Intent().apply{
            putExtra(EXSTRA_ANSWER_SHOW,true)
        }
        setResult(Activity.RESULT_OK,data)
    }
}

