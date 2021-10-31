package com.example.geoquiz

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

const val EXTRA_ANSWER_SHOWN="com.bignerdranch.kt.android.geoquiz.answer_shown"

private const val EXTRA_ANSWER_IS_TRUE=
    "com.bignerdranch.kt.android.geoquiz.answer_is_true"

private var answerIsTrue=false




class CheatActivity : AppCompatActivity() {

    private lateinit var answerTextView:TextView

    private lateinit var showAnswerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)


        answerIsTrue=intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false)

        answerTextView=findViewById(R.id.answer_text)

        showAnswerButton=findViewById(R.id.show_answer_button)

        showAnswerButton.setOnClickListener {
            val answerText= when{

                answerIsTrue-> R.string.true_button
                else-> R.string.false_button
            }
            answerTextView.setText(answerText)
            true.setAnswerShownResult(true)


        }


    }

    private fun setAnswerShownResult (isAnswerShown:Boolean) {
        
        
        val data =Intent().apply {
           setResult(EXTRA_ANSWER_SHOWN,this,isAnswerShown)

        }

        setResult(Activity.RESULT_OK,data)

    }


    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Int): Intent{

            return Intent(packageContext,CheatActivity::class.java) .apply {
                putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue)
            }


        }
    }


}








