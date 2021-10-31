package com.example.geoquiz


import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModelProvider

private const val REQUEST_CODE_CHEAT=1


class MainActivity : AppCompatActivity() {



    private lateinit var falseButton: Button
    private lateinit var trueButton: Button
    private lateinit var nextButton: Button
    private lateinit var previousButton: Button
    private lateinit var cheatButton: Button
    private lateinit var questionTv: TextView

    private val quizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }


   override fun onActivityResult(requestCode:Int,resultCode: Int,data:Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) {

            return

        }
        if (requestCode == REQUEST_CODE_CHEAT) {
         quizViewModel.isCheater=
             data?.getBooleanExtra(EXTRA_ANSWER_SHOWN,false)?: false

        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        buttons()
        updateQuestion()
        listeners()

    }

    private fun listeners() {
        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }
        previousButton.setOnClickListener {
            quizViewModel.previousQuestion()
            updateQuestion()
        }

        cheatButton.setOnClickListener {

          val intent= Intent(this,CheatActivity::class.java)
            startActivity(intent)

        }
        updateQuestion()
    }
    private fun updateQuestion() {

        val questionId = quizViewModel.currentQuestion
        questionTv.setText(questionId)

    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer: Boolean = quizViewModel.currentAnswer
    val massageResId= when {

        quizViewModel.isCheater -> R.string.judgemental_massage_toast
        userAnswer == correctAnswer -> R.string.correct_toast
        else -> R.string.incorrect_toast

    }
       Toast.makeText(this,massageResId,Toast.LENGTH_LONG)
           .show()

    }

    private fun buttons() {
        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        cheatButton = findViewById(R.id.cheatButton)
        questionTv = findViewById(R.id.questionTv)
    }
}







