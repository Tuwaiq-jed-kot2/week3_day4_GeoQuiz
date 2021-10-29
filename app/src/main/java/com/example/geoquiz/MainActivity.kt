package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
private const val KEY_INDEX = "Index"
private const val REQUEST_CODE_CHEAT = 1
const val Question_TO_CheatActivity = "azzam" //Week3 Day4 HomeWork Solution
const val EXTRA_ANS_T_ot_f = "trueOrFalse"
/*------------------------------------------------------------------------------*/
class MainActivity : AppCompatActivity() {
    /*------------------------------------------------------------------------------*/
   private lateinit var falseButton:Button
    /*------------------------------------------------------------------------------*/
   private lateinit var trueButton:Button
    /*------------------------------------------------------------------------------*/
   private lateinit var questionTextView:TextView
    /*------------------------------------------------------------------------------*/
   private lateinit var Next_btn:Button
    /*------------------------------------------------------------------------------*/
   private lateinit var Back_btn:Button
    /*------------------------------------------------------------------------------*/
   private lateinit var Cheat_btn:Button
    /*------------------------------------------------------------------------------*/
   private val quizViewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java)}
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK){
            return
        }
        if(requestCode == REQUEST_CODE_CHEAT){
            quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_ANSWER_SHOWN , false) ?:false
        }
    }
    /*------------------------------------------------------------------------------*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*------------------------------------------------------------------------------*/
        val currntIndex = savedInstanceState?.getInt(KEY_INDEX) ?: 0
        /*------------------------------------------------------------------------------*/
        quizViewModel.currentIndex = currntIndex
        /*------------------------------------------------------------------------------*/
        setContentView(R.layout.activity_main)
        /*------------------------------------------------------------------------------*/
        falseButton = findViewById(R.id.false_button)
        /*------------------------------------------------------------------------------*/
        trueButton = findViewById(R.id.true_button)
        /*------------------------------------------------------------------------------*/
        Next_btn = findViewById(R.id.Next_btn)
        /*------------------------------------------------------------------------------*/
        Back_btn = findViewById(R.id.Back_btn)
        /*------------------------------------------------------------------------------*/
        Cheat_btn = findViewById(R.id.Cheat_btn)
        /*------------------------------------------------------------------------------*/
        questionTextView = findViewById(R.id.Question_Tv)
        /*------------------------------------------------------------------------------*/
        /*------------------------------------------------------------------------------*/
        falseButton.setOnClickListener {
            checkAnswer(false)
        }
        /*------------------------------------------------------------------------------*/
        trueButton.setOnClickListener {
            checkAnswer(true)
        }
        /*------------------------------------------------------------------------------*/
        Next_btn.setOnClickListener {
            quizViewModel.NextQuestion()
            updateQuestion()
        }
        /*------------------------------------------------------------------------------*/
        Back_btn.setOnClickListener {
            quizViewModel.PreQuestion()
            updateQuestion()
        }
        /*------------------------------------------------------------------------------*/
        Cheat_btn.setOnClickListener {
            val intent = Intent(this , cheatActivity::class.java)
            intent.putExtra(EXTRA_ANS_T_ot_f , quizViewModel.currentAnswer)
            intent.putExtra(Question_TO_CheatActivity , quizViewModel.currentQuestion) //Week3 Day4 HomeWork Solution
            startActivityForResult(intent , REQUEST_CODE_CHEAT)
        }
        /*------------------------------------------------------------------------------*/
    }
    /*------------------------------------------------------------------------------*/
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
        outState.putInt(KEY_INDEX, quizViewModel.currentQuestion) //Week3 Day4 HomeWork Solution
    }
    /*------------------------------------------------------------------------------*/
    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestion
        questionTextView.setText(questionTextResId)
    }
    /*------------------------------------------------------------------------------*/
    private fun checkAnswer(userAnswer:Boolean){
        val currectAnswer = quizViewModel.currentAnswer
        val toastMsg = when {
            quizViewModel.isCheater -> R.string.Cheater_Msg
            userAnswer == currectAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }
        Toast.makeText(this , toastMsg , Toast.LENGTH_LONG).show()
    }
}
