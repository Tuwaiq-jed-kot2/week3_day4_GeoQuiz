package com.example.geoquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

const val KEYINDEX="lolololo"
const val requsetCheatC=11111
const val Qtocheat="jkjkjkj"

class MainActivity : AppCompatActivity() {

   private lateinit var questions:TextView
   private lateinit var falseButton:Button
   private lateinit var trueButton:Button
   private lateinit var nextButton:Button
   private lateinit var backButton:Button
   private lateinit var seeButton:Button


    private val quizViewModel by lazy { ViewModelProvider(this)
        .get(QuizViewModel::class.java) }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != RESULT_OK){
            return
        }
        if (requestCode == requsetCheatC){
         quizViewModel.Cheater =
         data?.getBooleanExtra(EXTRAcheat,false)?:false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initThings()
        updateQ()

        falseButton.setOnClickListener {
            Toast.makeText(this,R.string.incorrect_toast,Toast.LENGTH_LONG).show()

            checkA(false)
        }

        trueButton.setOnClickListener {
            Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_LONG).show()
            checkA(true)

        }
        nextButton.setOnClickListener{
        quizViewModel.nextQ()
            updateQ()

        }
        backButton.setOnClickListener{
            quizViewModel.backQ()
            BackQ()

        }
        seeButton.setOnClickListener {
            val intent=Intent(this,Cheat_activity::class.java)
            intent.putExtra(EXTRAanswerORnot,quizViewModel.currentAtheQ)
            intent.putExtra(Qtocheat,quizViewModel.currentText)
            startActivityForResult(intent,requsetCheatC)
        }

    }

    private fun initThings() {
        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton = findViewById(R.id.next_button)
        backButton = findViewById(R.id.back_button)
        seeButton = findViewById(R.id.showA_button)
        questions = findViewById(R.id.QTv)
    }
    private fun updateQ(){
        val QtextRes=quizViewModel.currentText
        questions.setText(QtextRes)

    }
    private fun BackQ(){
        val QtextRes=quizViewModel.currentText
        questions.setText(QtextRes)
    }
    private fun checkA (userA:Boolean){
        val corrictA= quizViewModel.currentAtheQ
        val toastMessge= when{
            quizViewModel.Cheater -> R.string.CheatA_toast
            userA ==corrictA -> R.string.correct_toast
            else->R.string.incorrect_toast
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEYINDEX,quizViewModel.currentIndex)
        outState.putInt(KEYINDEX,quizViewModel.currentText)
    }

}