package com.example.geoquiz

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import android.os.Parcelable

import java.util.ArrayList




private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"
private const val KEY_SCORE = "score"
private const val KEY_CHEAT_COUNT = "cheat_count"
private const val KEY_QUESTIONS_INDEXES = "Question_indexes"

const val EXTRA_QUESTION = "MainActivity_Question"
const val EXTRA_ANSWER = "MainActivity_Question_Answer"
private const val REQUAST_CODE_CHEAT = 0

class MainActivity : AppCompatActivity() {

    //Widgets
    private lateinit var scoreTextView: TextView
    private lateinit var questionTextView: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var previousButton: ImageButton
    private lateinit var cheatButton: Button

    //Attributes
    private val quizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quizViewModel.currentIndex = savedInstanceState?.getInt(KEY_INDEX) ?: 0
        quizViewModel.score = savedInstanceState?.getInt(KEY_SCORE) ?: 0
        quizViewModel.cheatCount = savedInstanceState?.getInt(KEY_CHEAT_COUNT) ?: 0

//        for (i in 0..quizViewModel.questionBankSize){
//            var indexToInt = savedInstanceState?.getInt(i.toString()) ?: 0
//            quizViewModel.i
//        }

        initWidgets()
        setClickListeners()
        updateQuestion()
        updateCheatButtonText()
        updateScore()

        if (quizViewModel.cheatCount == 2){
            gameOver()
        }
    }

    //Overrides
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
        outState.putInt(KEY_SCORE, quizViewModel.score)
        outState.putInt(KEY_CHEAT_COUNT, quizViewModel.cheatCount)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) return

        if (requestCode == REQUAST_CODE_CHEAT) {
            quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_CHEATED, false) ?: false
            quizViewModel.cheatCount++
            updateCheatButtonText()
        }
    }

    //Functions
    private fun initWidgets(){
        scoreTextView = findViewById(R.id.scoreTextView)
        questionTextView = findViewById(R.id.questionTextView)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.nextButton)
        previousButton = findViewById(R.id.previousButton)
        cheatButton = findViewById(R.id.cheatButton)
        scoreTextView.setText(R.string.score)
    }

    private fun setClickListeners(){

        questionTextView.setOnClickListener {
            quizViewModel.nextQuestion()
        }

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
            goToCheatActivity()
        }
    }

    private fun goToCheatActivity(){
        if (quizViewModel.cheatCount == 3){
            Toast.makeText(this, R.string.cheatCountToast, Toast.LENGTH_SHORT).show()
        }else {
            val intent = Intent(this, CheatActivity::class.java)

            intent.putExtra(EXTRA_QUESTION, quizViewModel.currentQuestionText)
            intent.putExtra(EXTRA_ANSWER, quizViewModel.currentQuestionAnswer)

            startActivityForResult(intent, REQUAST_CODE_CHEAT)
        }
    }

    private fun updateQuestion(){
        val questionText = quizViewModel.currentQuestionText
        questionTextView.setText(questionText)
        isQuestionAnswered()
    }

    private fun checkAnswer(answer: Boolean){
        val toastMessage: Int
        if (answer == quizViewModel.currentQuestionAnswer){
            toastMessage = R.string.correct_toast
            questionBeenAnswered()
            quizViewModel.score++
            updateScore()
        }else {
            toastMessage = R.string.Incorrect_toast
            questionBeenAnswered()
        }
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
    }

    private fun isQuestionAnswered(){
        trueButton.isEnabled = !quizViewModel.isCurrentQuestionAnswered
        falseButton.isEnabled = !quizViewModel.isCurrentQuestionAnswered
    }

    private fun questionBeenAnswered(){
        quizViewModel.isCurrentQuestionAnswered = true
        isQuestionAnswered()
    }

    @SuppressLint("SetTextI18n")
    private fun updateScore(){
        scoreTextView.text = "${quizViewModel.score}/10"
    }

    private fun updateCheatButtonText(){
        cheatButton.text = "Cheat ${quizViewModel.cheatCount}/3"
    }

    private fun gameOver() {
        questionTextView.setText(R.string.gameOver)
        trueButton.isVisible = false
        falseButton.isVisible = false
        nextButton.isVisible = false
        previousButton.isVisible = false
        cheatButton.isEnabled = false
    }
}