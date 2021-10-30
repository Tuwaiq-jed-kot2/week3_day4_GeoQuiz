package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider


private const val KEY_INDEX = "index"
const val KEY_QUESTION = "question"
const val KEY_ANSWER = "answer"
private const val REQUEST_CODE_CHEAT = 0

class MainActivity : AppCompatActivity() {

   private lateinit var trueBtn:Button
    private lateinit var falseBtn:Button
    private lateinit var nextBtn:Button
    private lateinit var cheatbtn:Button
    private lateinit var questionTV:TextView


    private val TAG = "Main_Activity"

    private val quizViewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java) }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == REQUEST_CODE_CHEAT){
            quizViewModel.isCheater=data?.getBooleanExtra(EXTRA_ANSWER_SHOWN,false)?:false
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentIndex =  savedInstanceState?.getInt(KEY_INDEX) ?: 0
        Log.d(TAG,"bundel : $currentIndex")
        quizViewModel.currentIndex = currentIndex


        init_view()

        listeners()

        updateQuestion()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "a value has been saved")
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    private fun listeners() {
        trueBtn.setOnClickListener {
            checkAnswer(true)
        }

        falseBtn.setOnClickListener {
            checkAnswer(false)
        }

        nextBtn.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()

        }

        cheatbtn.setOnClickListener {
            val intent = Intent(this, CheatActivity::class.java)
            intent.putExtra(KEY_QUESTION,quizViewModel.currentQuestion)
            intent.putExtra(KEY_ANSWER,quizViewModel.currentAnswer)
            //startActivity(intent)
           startActivityForResult(intent, REQUEST_CODE_CHEAT)
        }
    }

    private fun updateQuestion(){
        val questionId = quizViewModel.currentQuestion
        questionTV.setText(questionId)
    }

    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer =  quizViewModel.currentAnswer

        val toastMassage = when{
            quizViewModel.isCheater -> R.string.cheater_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }

        Toast.makeText(this,toastMassage,Toast.LENGTH_LONG).show()

    }

    private fun init_view() {
        trueBtn = findViewById(R.id.true_btn)
        falseBtn = findViewById(R.id.false_btn)
        nextBtn = findViewById(R.id.next_btn)
        cheatbtn = findViewById(R.id.cheat_btn)
        questionTV = findViewById(R.id.question_tv)
    }
}