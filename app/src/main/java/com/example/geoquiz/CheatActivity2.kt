package com.example.geoquiz

import android.content.Intent
import android.icu.text.UnicodeSetSpanner
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.easyquize.INDEX_ANSWER
import com.example.easyquize.Index_QUESTAION
import com.example.easyquize.REQUEST_CODE
import org.w3c.dom.Text


private  lateinit var warningText : TextView
private lateinit var textView3 : TextView
private lateinit var cheatBtn : Button
     var answerIsTrueOrFalse =false

class CheatActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat2)

         warningText=findViewById(R.id.warning_textview)
         cheatBtn= findViewById(R.id.showAnswer_btn)

        answerIsTrueOrFalse =intent.getBooleanExtra(Index_QUESTAION,false)
        cheatBtn.setOnClickListener {

        }


            val toast = Toast.makeText(this, "  No , you are CHEATER :( !", Toast.LENGTH_LONG)
            toast.show()
            toast.setGravity(Gravity.TOP, 100, 100)






    }

}