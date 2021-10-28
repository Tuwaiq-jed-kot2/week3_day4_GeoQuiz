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

const val EXTRA_SHOW_ANSWER = "CheatActivity"
const val EXTRA_SHOW_QUESTION = "CheatActivity"

private const val REQUEST_CODE_CHEAT = 0

private const val KEY_CHEATING_COUNT = "cheat limits reached"
private const val KEY_INDEX = "index"

const val TAG = "TAG"

class MainActivity : AppCompatActivity() {

   private lateinit var falseButton:Button
   private lateinit var trueButton:Button
   private lateinit var nextButton:Button
   private lateinit var previousButton:Button
   private lateinit var cheatButton:Button
   private lateinit var questionTv:TextView
   private lateinit var writtenBy:TextView

   private val quizViewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quizViewModel.currentIndex = savedInstanceState?.getInt(KEY_INDEX) ?: 0
        quizViewModel.cheatingCount = savedInstanceState?.getInt(KEY_CHEATING_COUNT) ?: 3



        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        cheatButton = findViewById(R.id.cheat_button)
        questionTv = findViewById(R.id.question_text_view)
        writtenBy = findViewById(R.id.written_by)


        falseButton.setOnClickListener {
            checkAnswer(false)

        }

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        nextButton.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
            updateButtons()
        }

        previousButton.setOnClickListener {
            quizViewModel.previousQuestion()
            updateQuestion()
            updateButtons()
        }

        cheatButton.setOnClickListener {
            val intent = Intent(this,CheatActivity::class.java)
            intent.putExtra(EXTRA_SHOW_ANSWER,quizViewModel.currentQuestionAnswer)
            intent.putExtra(EXTRA_SHOW_QUESTION,quizViewModel.currentQuestion)
            startActivityForResult(intent, REQUEST_CODE_CHEAT)
            updateButtons()
        }

        updateQuestion()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG," a value been saved")
        outState.putInt(KEY_INDEX,quizViewModel.currentIndex)
        outState.putInt(KEY_CHEATING_COUNT,quizViewModel.cheatingCount)
    }


    private fun updateQuestion() {
        questionTv.setText(quizViewModel.currentQuestion)
        writtenBy.text = quizViewModel.currentQuestionWriter
        if (quizViewModel.cheatingCount < 2 ){
            falseButton.isEnabled = false
            trueButton.isEnabled = false
            nextButton.isEnabled = false
            previousButton.isEnabled = false
            cheatButton.isEnabled = false
            questionTv.setText(R.string.game_over)
        }
    }

    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer

        if (userAnswer == correctAnswer){
            Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_LONG).show()
            quizViewModel.currentQuestionIsAnswered = true
            updateButtons()


        }else{
            Toast.makeText(this,R.string.incorrect_toast,Toast.LENGTH_LONG).show()
        }
    }

    private fun updateButtons() {
        if (quizViewModel.currentQuestionIsAnswered) {
            falseButton.isEnabled = false
            trueButton.isEnabled = false
        }else{
            falseButton.isEnabled = true
            trueButton.isEnabled = true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK){
            return
        }

        if (requestCode == REQUEST_CODE_CHEAT){
            quizViewModel.cheatingCount--
        }
    }
}