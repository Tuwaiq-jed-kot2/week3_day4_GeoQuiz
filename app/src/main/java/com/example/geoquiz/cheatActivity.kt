package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

const val Extra_Answer_showen = "wtf sheater"


class cheatActivity : AppCompatActivity() {
    private lateinit var answer:TextView
    private lateinit var showAnswerBtn :Button
    private lateinit var cheat :TextView
    var answerIsTrueOrFalse = false
    var question = 0
    private lateinit var show_question:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answer = findViewById(R.id.Answer_Tv)

        showAnswerBtn = findViewById(R.id.answerrrr)
        cheat = findViewById(R.id.cheat_Button)
        show_question = findViewById(R.id.question_Tv)


        answerIsTrueOrFalse= intent.getBooleanExtra(extra_answer_is_true_or_false,false)
        answer.setText(answerIsTrueOrFalse.toString())
        question=intent.getIntExtra(KEY_QU,0)
    }
    fun setAnswerShowenResult(){
        val data = Intent().apply {
            putExtra(Extra_Answer_showen,true)

        }

        setResult(Activity.RESULT_OK,data)

    }
}

