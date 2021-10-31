package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

const val EXTRA_ANSWER_SHOWN="cheater !!!"
var answerIsTrueIrFalse = false


class  CheatActivity : AppCompatActivity() {

    private lateinit var showAnswerButton: Button
    private lateinit var answerTV: TextView
    private lateinit var questionTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)


        showAnswerButton=findViewById(R.id.showAnswer)
        answerTV=findViewById(R.id.Answer_TV)
        questionTv = findViewById(R.id.showQ)

        var cheatq =0

        answerIsTrueIrFalse =intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE_OR_FALSE,false)
        cheatq = intent.getIntExtra(Extrequestion,0)



        showAnswerButton.setOnClickListener {
            answerTV.text = answerIsTrueIrFalse.toString()
            questionTv.setText(cheatq)
            setAnswerShownResult()
        }
    }
        private fun setAnswerShownResult(){
            val intent = Intent().apply {
                putExtra(EXTRA_ANSWER_SHOWN, true)
            }
            setResult(Activity.RESULT_OK,intent)
        }
    }









