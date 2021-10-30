package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import java.time.Instant
const val EXTRAcheat="tgtgtgt"
const val EXTRAanswerORnot="mnmnmn"

class Cheat_activity : AppCompatActivity(){
    private lateinit var answer:TextView
    private lateinit var showA:Button
    var isTorF=false
    var questionR=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        answer=findViewById(R.id.answerTextV)
        showA=findViewById(R.id.showA_button)
        answer=findViewById(R.id.QTv)

        questionR=intent.getIntExtra(Qtocheat,0)
        isTorF=intent.getBooleanExtra(EXTRAanswerORnot,true)

        showA.setOnClickListener{
            answer.setText(isTorF.toString())
            answerShowRe()
        }
    }
    fun answerShowRe(){
        val data =Intent().apply{
            putExtra(EXTRAcheat,true)
        }
        setResult(Activity.RESULT_OK)
    }

}



