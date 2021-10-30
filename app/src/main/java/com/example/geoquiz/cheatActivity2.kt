package com.example.geoquiz

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapplicationreplaning.EXTRA_ANSWER_IS_TRUE
import com.example.firstapplicationreplaning.KEY_OF_INDIX

const val EXTRA_CHEATER_SHOWEN=" CHEATER!!!"

class cheatActivity : AppCompatActivity() {//1

    private lateinit var WarmTV: TextView
    private lateinit var answerTV: TextView
    private lateinit var questionCheatTV: TextView
    private lateinit var showAnswerBTN: Button
    var answerIntint=false

val EXTRA_CHEATER_SHOWEN=" CHEATER!!!"

class cheatActivity : AppCompatActivity() {//1

    private lateinit var WarmTV: TextView
    private lateinit var answerTV: TextView
    private lateinit var questionCheatTV: TextView
    private lateinit var showAnswerBTN: Button
    var answerIntint=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat2)

        answerIntint=intent.getBooleanExtra(EXTRA_CHEATER_SHOWEN,false)
        WarmTV=findViewById(R.id.tixtView)
        answerTV=findViewById(R.id.cheat_aswer)
        questionCheatTV=findViewById(R.id.qustion_of_answer)
        showAnswerBTN=findViewById(R.id.button)
        showAnswerBTN.setOnClickListener {
            answerTV.setText(answerTV.toString())



            val intent=getIntent()
            answerTV.setText(intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false).toString())
            questionCheatTV.setText(intent.getIntExtra(KEY_OF_INDIX,0))

        }

    }


    fun setAnswerShowResult(){
        val data= intent.apply{
            putExtra(EXTRA_CHEATER_SHOWEN,true)

        }
        setResult(Activity.RESULT_OK,data)
    }

}

}
