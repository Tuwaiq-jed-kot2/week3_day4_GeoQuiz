package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

const val EXTRA_CHEATED = "CheatActivity_Cheated"
class CheatActivity : AppCompatActivity() {

    //Widgets
    private lateinit var questionTextView: TextView
    private lateinit var answerTextView: TextView
    private lateinit var cheatButton: Button

    //Attributes
    var questionTextResId = 0
    var answer = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        questionTextResId = intent.getIntExtra(EXTRA_QUESTION, R.string.warning_message)
        answer = intent.getBooleanExtra(EXTRA_ANSWER, false)

        initWidgets()
        setClickListeners()
    }

    private fun initWidgets(){
        questionTextView = findViewById(R.id.questionTextView)
        answerTextView = findViewById(R.id.answerTextView)
        cheatButton = findViewById(R.id.showAnswerButton)

        questionTextView.setText(questionTextResId)

    }

    private fun setClickListeners(){
        cheatButton.setOnClickListener {
            answerTextView.text = answer.toString().uppercase()
            cheatButtonPressed()
        }
    }

    private fun cheatButtonPressed(){
        val data = Intent().apply {
            putExtra(EXTRA_CHEATED, true)
        }

        setResult(Activity.RESULT_OK, data)
    }

}