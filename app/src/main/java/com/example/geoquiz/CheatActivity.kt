package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

const val EXTRA_ANSWER_SHOWEN ="shown answer is cheater"
const val EXTRA_QUSTION_SHOWEN ="kksdl"
private lateinit var showAnserBtn: Button
private lateinit var aswerShowTv:TextView
private lateinit var ShowQustionTv:TextView

var anserIsTrueOrFalse = false
var questionTextId = 0

class CheatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)


        showAnserBtn = findViewById(R.id.worrnign_button)
        aswerShowTv = findViewById(R.id.showAns_Tv)

        questionTextId = intent.getIntExtra(KEY_EXTRA_QUSTION, R.string.worrnning_messege)
        ShowQustionTv = findViewById(R.id.qusu_Tv)
        ShowQustionTv.setText(questionTextId)



        anserIsTrueOrFalse = intent.getBooleanExtra(KEY_EXTRA_IS_TRUE,false)


        // in set clickable for cheat button لازم الي في القت بولين اكسترا الكي نفسه في الانتينت intent الموجود في المين


        showAnserBtn.setOnClickListener {
            aswerShowTv.setText(anserIsTrueOrFalse.toString())
            setAnswerShownResult()
        }


    }



     fun setAnswerShownResult(){
        val data = Intent().putExtra(EXTRA_ANSWER_SHOWEN,true)

         setResult(Activity.RESULT_OK,data)
    }
}