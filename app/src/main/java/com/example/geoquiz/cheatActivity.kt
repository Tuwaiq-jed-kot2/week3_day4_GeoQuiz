package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

const val EXTRA_ANSWER_SHOWN = "cheater !!!"
/*------------------------------------------------------------------------------*/
class cheatActivity : AppCompatActivity() {
    private lateinit var AnswerTv:TextView
    /*------------------------------------------------------------------------------*/
    private lateinit var question_Tv:TextView //Week3 Day4 HomeWork Solution
    /*------------------------------------------------------------------------------*/
    private lateinit var showAnswer_btn:Button
    /*------------------------------------------------------------------------------*/
    var answerIsTrueOrFalse = false
    /*------------------------------------------------------------------------------*/
    var questionResId = 0 //Week3 Day4 HomeWork Solution
    /*------------------------------------------------------------------------------*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        /*------------------------------------------------------------------------------*/
        question_Tv = findViewById(R.id.questionResId) //Week3 Day4 HomeWork Solution
        /*------------------------------------------------------------------------------*/
        AnswerTv = findViewById(R.id.Answer_Tv)
        /*------------------------------------------------------------------------------*/
        showAnswer_btn = findViewById(R.id.Show_btn)
        /*------------------------------------------------------------------------------*/
        questionResId = intent.getIntExtra(Question_TO_CheatActivity, 0) //Week3 Day4 HomeWork Solution
        /*------------------------------------------------------------------------------*/
        answerIsTrueOrFalse = intent.getBooleanExtra(EXTRA_ANS_T_ot_f, false)
        /*------------------------------------------------------------------------------*/
        question_Tv.setText(questionResId) //Week3 Day4 HomeWork Solution
        /*------------------------------------------------------------------------------*/
        showAnswer_btn.setOnClickListener {
            AnswerTv.setText(answerIsTrueOrFalse.toString())
            setAnswerShownResult()
        }
        /*------------------------------------------------------------------------------*/
    }
    /*------------------------------------------------------------------------------*/
        fun setAnswerShownResult(){
            val data = Intent().apply {
                putExtra(EXTRA_ANSWER_SHOWN , true)
            }
            setResult(Activity.RESULT_OK , data)
    }
}
