package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider

const val EXTRA_ANSWER_IS_TRUE_OR_NOT = "abc"
private const val REQUEST_CODE_CHEAT = 0
const val EXTRA_QUESTION = "question"
class MainActivity : AppCompatActivity() {

   private lateinit var falseButton:Button
   private lateinit var trueButton:Button
   private lateinit var nextBtn:Button
   private lateinit var backBtn: Button
   private lateinit var cheatBtn:Button
   private lateinit var questionTV : TextView

   private val quizViewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initilaze()

        widgets()

        nextQuestion()
    }

    private fun MainActivity.widgets() {
        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        trueButton.setOnClickListener {
            checkAnswer(true)

        }

        nextBtn.setOnClickListener {
            quizViewModel.nextQues()
            nextQuestion()
        }

        backBtn.setOnClickListener {
            quizViewModel.previousQues()
            nextQuestion()

        }

        cheatBtn.setOnClickListener {
            val intent = Intent(this, CheatActivity::class.java)
            intent.putExtra(EXTRA_ANSWER_IS_TRUE_OR_NOT, quizViewModel.currentAnswer)
            intent.putExtra(EXTRA_QUESTION, quizViewModel.currentQuestion)
            startActivityForResult(intent, REQUEST_CODE_CHEAT)

        }
    }

    private fun initilaze() {
        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextBtn = findViewById(R.id.next_btn)
        backBtn = findViewById(R.id.back_btn)
        cheatBtn = findViewById(R.id.cheat_btn)
        questionTV = findViewById(R.id.ques_tv)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK){
            return
        }
        if (requestCode == REQUEST_CODE_CHEAT){
            quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_ANSWER_IS_TRUE_OR_NOT,false) ?: false
        }
    }

    private fun nextQuestion(){
        val questionText = quizViewModel.currentQuestion
        questionTV.setText(questionText)

    }

    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = quizViewModel.currentAnswer

        val message = when{
            quizViewModel.isCheater -> R.string.cheat_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast

        }

        Toast.makeText(this,message,Toast.LENGTH_LONG).show()


    }






}