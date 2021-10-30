package com.example.geoquiz

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

const val KEY_INDEX = "index"
const val KEY_ANSWER = "answer"
const val KEY_QUESTION = "question"


class MainActivity : AppCompatActivity() {

    private lateinit var falseButton:Button
    private lateinit var trueButton:Button
    private lateinit var nextButton:Button
    private lateinit var backButton:Button
    private lateinit var questionTv:TextView
    private lateinit var scoreTv:TextView
    private lateinit var answerIc:ImageView
    private lateinit var tryAgainBtn:Button
    private lateinit var mainLayout:LinearLayout
    private lateinit var secondLayout:LinearLayout
    private lateinit var showAnswerBtn:TextView
    private lateinit var cheatAttemptsTv:TextView
    private lateinit var cheaterTv:TextView

    private val quizViewModel by lazy{ViewModelProvider(this).get(QuizViewModel::class.java)}



    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton = findViewById(R.id.next_question)
        questionTv = findViewById(R.id.question_tV)
        backButton = findViewById(R.id.back_question)
        scoreTv = findViewById(R.id.score)
        answerIc = findViewById(R.id.answer_ic)
        tryAgainBtn = findViewById(R.id.try_again_btn)
        mainLayout = findViewById(R.id.main_layout)
        secondLayout = findViewById(R.id.second_layout)
        showAnswerBtn = findViewById(R.id.show_answer_btn)
        cheatAttemptsTv = findViewById(R.id.attempts_tv)
        cheaterTv = findViewById(R.id.cheaterTv)

        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    quizViewModel.questionBank[quizViewModel.currentIndex].isCheated =
                        data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
                    quizViewModel.cheatingCounter = data?.getIntExtra(CHEAT_COUNTER, 3) ?: 3
                    cheatAttemptsTv.text = quizViewModel.cheatingCounter.toString()
                    if (quizViewModel.cheatingCounter == 0){
                        showAnswerBtn.isEnabled = false
                        showAnswerBtn.setTextColor(Color.GRAY)
                    }
                }
            }

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX) ?: 0

        quizViewModel.currentIndex = currentIndex

        scoreTv.text = quizViewModel.currentScore.toString()

        cheatAttemptsTv.text = quizViewModel.cheatingCounter.toString()

        isSolved()

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        nextButton.setOnClickListener {
            if (quizViewModel.questionBank[9].isSolved){
                mainLayout.visibility = View.INVISIBLE
                secondLayout.visibility = View.VISIBLE
            }else
                quizViewModel.nextQuestion()
            updateQuestion()
            answerIc.visibility = View.INVISIBLE
            falseButton.isEnabled = true
            trueButton.isEnabled = true
            val color = Color.WHITE
            falseButton.backgroundTintList = ColorStateList.valueOf(color)
            trueButton.backgroundTintList = ColorStateList.valueOf(color)
            isSolved()
        }

        backButton.setOnClickListener {
            quizViewModel.backQuestion()
            updateQuestion()
            answerIc.visibility = View.INVISIBLE
            isSolved()
        }

        showAnswerBtn.setOnClickListener {
            val intent = Intent(this, CheatActivity::class.java)
            intent.putExtra(KEY_ANSWER, quizViewModel.currentQuestionAnswer)
            intent.putExtra(KEY_QUESTION, quizViewModel.currentQuestionText)
            resultLauncher.launch(intent)
        }

        tryAgainBtn.setOnClickListener {
            updateQuestion()
            quizViewModel.tryAgain()

            mainLayout.visibility = View.VISIBLE
            secondLayout.visibility = View.INVISIBLE

            falseButton.isEnabled = true
            trueButton.isEnabled = true
            falseButton.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            trueButton.backgroundTintList = ColorStateList.valueOf(Color.WHITE)

            scoreTv.text = quizViewModel.currentScore.toString()
        }

        updateQuestion()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(KEY_INDEX , quizViewModel.currentIndex)
    }


    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        questionTv.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        if (correctAnswer == userAnswer){
            quizViewModel.correctScore()
            scoreTv.text = quizViewModel.currentScore.toString()
            if ( quizViewModel.questionBank[quizViewModel.currentIndex].isCheated){
                cheaterTv.visibility = View.VISIBLE
            }else{
                answerIc.setImageResource(R.drawable.ic_right)
                answerIc.visibility = View.VISIBLE
                cheaterTv.visibility = View.INVISIBLE
            }
            isSolved()
        }else {
            quizViewModel.questionBank[quizViewModel.currentIndex].isSolved = true
            quizViewModel.wrongScore()
            scoreTv.text = quizViewModel.currentScore.toString()
            answerIc.setImageResource(R.drawable.ic_wrong)
            answerIc.visibility = View.VISIBLE
            cheaterTv.visibility = View.INVISIBLE
            isSolved()
        }
    }

    private fun isSolved(){
        if (quizViewModel.questionBank[quizViewModel.currentIndex].isSolved){
            falseButton.isEnabled = false
            trueButton.isEnabled = false
            falseButton.backgroundTintList = ColorStateList.valueOf(Color.GRAY)
            trueButton.backgroundTintList = ColorStateList.valueOf(Color.GRAY)
        }
    }
}