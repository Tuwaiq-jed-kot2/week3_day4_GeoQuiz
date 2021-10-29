package com.example.geoquiz


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

const val ANSWER_SHOWN = "Answer Shown"

class CheatActivity : AppCompatActivity() {

    var answer = false
    var question = 0
    private lateinit var answertText : TextView
    private lateinit var showAnswer : Button
    private lateinit var showQuestion : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answer = intent.getBooleanExtra(Extra_ANSWER,false)
        question = intent.getIntExtra(Extra_QUESTION,0)

        answertText=findViewById(R.id.cheat_answer)
        showQuestion = findViewById(R.id.cheatQuestion)
        showAnswer = findViewById(R.id.show_answerBtn)

        showQuestion.setText(question)

        showAnswer.setOnClickListener {
            answertText.setText(answer.toString().uppercase())
            Toast.makeText(this, R.string.cheater_toast, Toast.LENGTH_SHORT).show()

        }
    }

}