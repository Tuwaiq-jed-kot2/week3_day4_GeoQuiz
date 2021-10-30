package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
const val  EXTRA_ANSWER_SHOWN="ABCD"
class cheatActivity : AppCompatActivity() {
    private lateinit var answer_tv:TextView
    private  lateinit var showAnswer:Button
    private lateinit var  quesChetext:TextView
    var answerIsTrueOrFalse=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)


        answer_tv=findViewById(R.id.answer_tv)
        showAnswer=findViewById(R.id.show_answer)
        quesChetext=findViewById(R.id.question_up)

         val cheQuest=intent.getIntExtra(Question_Bankch,0)
         quesChetext.setText(cheQuest)

        showAnswer.setOnClickListener {
            answerIsTrueOrFalse=intent.getBooleanExtra(Extra_ANSWER_IS_TRUE,false)
            answer_tv.setText(answerIsTrueOrFalse.toString())
            setAnswerShownResult()
        }
    }
    fun setAnswerShownResult(){
        val  data =Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN,true)
        }
        setResult(Activity.RESULT_OK,data)
    }
}