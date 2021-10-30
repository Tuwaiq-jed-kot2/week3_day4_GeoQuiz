package com.example.newgeoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.R

const val EXTRA_ANSWER_SHOW= "The Cheater Of The Year"
class Cheat_activity : AppCompatActivity() {

    private lateinit var answerTv:TextView
    private lateinit var showAnswerBtn:Button
    private lateinit var theQuestion: TextView

                     var answer=false
                      var questionResId=0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
cipy()
    }

    fun cipy(){
        showAnswerBtn=findViewById(R.id.show_answer_btn)
        answerTv=findViewById(R.id.cheat_answer_tv)
       theQuestion= findViewById (R.id.waring_textView)
        answer=intent.getBooleanExtra(EXTRA_W_T_or_f,false)
        questionResId=intent.getIntExtra(questionTOCheatActivity,0)
        // some thing missing right here
        theQuestion.setText(R.id.question_tv)
        showAnswerBtn.setOnClickListener { answerTv.setText(answer.toString())
            setTheAnswerResult()

        }





    }
    fun setTheAnswerResult(){
        val data= Intent().apply { putExtra(EXTRA_ANSWER_SHOW,true) }
        setResult(Activity.RESULT_OK,data)
    }
}