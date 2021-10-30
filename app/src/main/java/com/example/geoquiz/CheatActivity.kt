package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

const val EXTRA_ANSWER_SHOWN = "answer shown"
const val CHEAT_COUNTER = "cheat counter"
var cheatCounter = 3

class CheatActivity : AppCompatActivity() {

    private lateinit var showAnswerBtn:Button
    private lateinit var cheatAnswerTv:TextView
    private lateinit var cheatedQtV:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        showAnswerBtn = findViewById(R.id.cheat_btn)
        cheatAnswerTv = findViewById(R.id.cheat_answer_tv)
        cheatedQtV = findViewById(R.id.cheated_q_tv)


        cheatedQtV.setText(intent.getIntExtra(KEY_QUESTION,0))

        showAnswerBtn.setOnClickListener {
            val answer =  intent.getBooleanExtra(KEY_ANSWER,true).toString()
            cheatAnswerTv.text = answer
            cheatCounter -= 1
            setAnswerShownResult()
        }

    }

    private fun setAnswerShownResult() {

        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, true)
            putExtra(CHEAT_COUNTER, cheatCounter)
        }
        setResult(Activity.RESULT_OK, data)
    }
}