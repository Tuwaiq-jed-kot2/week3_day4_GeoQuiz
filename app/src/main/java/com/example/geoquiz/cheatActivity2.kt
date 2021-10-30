package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

const val EXTRA_ANSWER_SHOWEN="whaaaay??"

class CheatActivity2 : AppCompatActivity() {


    private lateinit var showAnswerBTN: Button
    private lateinit var cheatAnswer: TextView
    private lateinit var cheatQ: TextView

    var answerForCheat=false
    var quesForCheat= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat2)

        showAnswerBTN=findViewById(R.id.showAnswerBTN)
        cheatAnswer=findViewById(R.id.cheatAnswer)
        cheatQ=findViewById(R.id.cheatQ)

        answerForCheat=intent.getBooleanExtra(EXTERA_TRUE_FALSE,false)
        quesForCheat= intent.getIntExtra(EXTERA_QUESTION,0)

        showAnswerBTN.setOnClickListener {
            cheatQ.setText(quesForCheat)
            cheatAnswer.setText(answerForCheat.toString())
        }
    }
    fun shownResult(){
        val data= Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWEN,true)
        }
        setResult(Activity.RESULT_OK,data)
    }
}