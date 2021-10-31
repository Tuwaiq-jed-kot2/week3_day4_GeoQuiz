package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import android.widget.TextView
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider

private const val KEY_INDEX ="index"
private const val KEYINDEX="index"
const val EXTRA_ANSWER_IS_TRUE_OR_FALSE="MainActivity.Answer"

private const val REQUEST_CODE_CHEAT= 999

const val Extrequestion ="your que"

class MainActivity : AppCompatActivity() {

    private lateinit var falseButton: Button
    private lateinit var trueButton: Button
    private lateinit var previousButton: ImageView
    private lateinit var nextButton: ImageView
    private lateinit var questionTextView: TextView
    private lateinit var instructorName: TextView
    private lateinit var cheatButton: Button

    private val Tag = "Main_Activity"
    private val quizViewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java) }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode != Activity.RESULT_OK){
            return
        }
         if(requestCode == REQUEST_CODE_CHEAT){
            quizViewModel.ischeater  =data?.getBooleanExtra(EXTRA_ANSWER_SHOWN,false) ?: false
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(Tag, "onCreate()")



        Log.d(Tag, "hi i'm view Model from main Activity $quizViewModel")

        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton = findViewById(R.id.nextButton)
        previousButton = findViewById(R.id.previousButton)
        questionTextView = findViewById(R.id.question_Tv)
        instructorName = findViewById(R.id.instructorName)
        cheatButton = findViewById(R.id.cheatButton)

        falseButton.setOnClickListener {
            checkAnswer(false)
        }
        trueButton.setOnClickListener {
            checkAnswer(true)
        }
        nextButton.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }
        questionTextView.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }
        previousButton.setOnClickListener {
            quizViewModel.previousQuestion()
            updateQuestion()
        }
        cheatButton.setOnClickListener {
           var intent = Intent(this,CheatActivity::class.java)
            intent.putExtra(EXTRA_ANSWER_IS_TRUE_OR_FALSE,quizViewModel.currentQuestionAnswer)
            intent.putExtra(Extrequestion,quizViewModel.currentQuestionText)
            startActivityForResult(intent,REQUEST_CODE_CHEAT)
        }
        updateQuestion()
    }




    override fun onSaveInstanceState(outState:Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX,quizViewModel.currentIndex)
        outState.putInt(KEYINDEX, quizViewModel.currentQuestionText)
    }



    private fun updateQuestion() {

        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {

        val correctAnswer = quizViewModel.currentQuestionAnswer

        val toastMassage = when{
            quizViewModel.ischeater ->R.string.judgement
            userAnswer ==  correctAnswer ->R.string.correct_toast
            else->R.string.incorrect_toast
        }
        Toast.makeText(this, toastMassage, Toast.LENGTH_LONG).show()

        }
    }

