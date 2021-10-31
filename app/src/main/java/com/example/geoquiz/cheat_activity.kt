package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.time.Instant
const val extrx_cheater="alpha"
const val ExtraAnsweerOrNOT="answeraaa"

class cheat_activity : AppCompatActivity() {
    private lateinit var answer:TextView
    private lateinit var showA:Button
    private lateinit var cheatButton: Button

    var answerIsTOrF= false
    var Equestonn=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        init1()

        Equestonn=intent.getIntExtra(Qtocheat,0)

        answerIsTOrF = intent.getBooleanExtra(ExtraAnsweerOrNOT, true)

        showA.setOnClickListener {
            answer.setText(answerIsTOrF.toString())
            setAnswerShowResult()
        }

    }

    private fun init1() {
        answer = findViewById(R.id.answerTextView)
        showA = findViewById(R.id.showAnswer)
        cheatButton = findViewById(R.id.cheat_button)

    }

    fun setAnswerShowResult(){

        val data=Intent().apply {
            putExtra(extrx_cheater,true)
        }
        setResult(Activity.RESULT_OK)

    }

}


