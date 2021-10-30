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


private const val KEY_INDEX="index"
const val ANSWER_PASSWORD = "pas"
const val QUESTION="Que"
 private const val REQEST_CODE_CHEAT=333
class MainActivity : AppCompatActivity() {
    private lateinit var falseButton: Button
    private lateinit var trueButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView:TextView
    private lateinit var backButton: Button
    private lateinit var cheatButton:Button


    val TAG ="Main_Activity"



    private val quizViewModel by lazy { ViewModelProvider(this)
        .get(QuizViewModel::class.java)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"onCreate")
        val currentIndex=savedInstanceState?.getInt(KEY_INDEX)  ?: 0

        quizViewModel.currentIndex=currentIndex



        Log.d(TAG,"hi im viewModel from MainActivity $quizViewModel")

        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton = findViewById(R.id.nextButton)
        questionTextView = findViewById(R.id.question_Tv)
        backButton = findViewById(R.id.BACK)
        cheatButton=findViewById(R.id.cheat_button)



        cheatButton.setOnClickListener {
            val intent=Intent(this, CheatActivity::class.java)
            intent.putExtra(ANSWER_PASSWORD,quizViewModel.currentQuestionAnswer)
            intent.putExtra(QUESTION,quizViewModel.currentQuestionText)

            startActivityForResult(intent, REQEST_CODE_CHEAT)


        }

        falseButton.setOnClickListener {
            checkAnswer(false)

        }

        trueButton.setOnClickListener {
            checkAnswer(true)
        }
        questionTextView.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }
        nextButton.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }
        updateQuestion()

        backButton.setOnClickListener {
           quizViewModel.backQuestion()
            updateQuestion()
        }
        updateQuestion()


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode!=Activity.RESULT_OK){
            return
        }

        if(requestCode== REQEST_CODE_CHEAT){
            quizViewModel.isCheater=data?.getBooleanExtra(EXTRA_ANSWER, false) ?: false


        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
       outState.putInt(KEY_INDEX,quizViewModel.currentIndex)
    }




    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume")
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart")
    }
    override fun onRestart(){

    super.onRestart()
    Log.d(TAG,"onRestart")
}

    override fun onPause(){

        super.onPause()
        Log.d(TAG,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy")
    }
    private fun updateQuestion(){
        val questionTextResId= quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer:Boolean) {





        val correctAnswer = quizViewModel.currentQuestionAnswer

        if (quizViewModel.isCheater) {
            Toast.makeText(this, R.string.judgement, Toast.LENGTH_LONG).show()
        }
        if (userAnswer == correctAnswer) {
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG).show()
        }
    }

}