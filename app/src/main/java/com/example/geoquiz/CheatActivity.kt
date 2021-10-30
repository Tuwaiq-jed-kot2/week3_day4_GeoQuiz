package com.example.geoquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
const val EXTRA_ANSWER="IT CHEATER!!!"
class CheatActivity : AppCompatActivity() {

   private lateinit var answer:TextView
   private lateinit var showAnswerBTN:Button
   private lateinit var cheatTextView:TextView

   var answerIsTrueOrFalse= false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answer=findViewById(R.id.answer)
        showAnswerBTN=findViewById(R.id.show_answer_button)
        cheatTextView=findViewById(R.id.cheat_ch)

        val cheated1=intent.getIntExtra(QUESTION,0)
        cheatTextView =setText(cheated1)

        val cheated=intent.getBooleanExtra(ANSWER_PASSWORD,false)
        answer.text= cheated.toString()



        showAnswerBTN.setOnClickListener {
            answerIsTrueOrFalse=intent.getBooleanExtra(ANSWER_PASSWORD, true)
            answer.setText(answerIsTrueOrFalse.toString())
            setAnswerShoanResult()
        }

    }

    fun setAnswerShoanResult(){
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER,true)
        }
        setResult(RESULT_OK,data)
    }




}