package com.example.geoquiz

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts


private const val KEY_INDEX = "index"
const val ANSWER = "Answer"
const val QUESTION = "Q"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var cheatButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var backButton: ImageButton
    private lateinit var hintButton: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var questionInstructorTextView: TextView
    private lateinit var appName: TextView
    private lateinit var progressBar: ProgressBar

    private val quizViewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java) }

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                quizViewModel.questionBank[quizViewModel.currentIndex].isCheater =
                    data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
                quizViewModel.cheatCount = data?.getIntExtra(CHEAT_COUNT, 0) ?: 0
            }
        }

    var trueCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX) ?: 0
        quizViewModel.currentIndex = currentIndex

        val currentQuestion = savedInstanceState?.getInt(KEY_INDEX) ?: 0
        quizViewModel.currentQuestionText = currentQuestion

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        backButton = findViewById(R.id.back_button)
        questionTextView = findViewById(R.id.question_Tv)
        questionInstructorTextView = findViewById(R.id.instructor)
        appName = findViewById(R.id.appName)
        cheatButton = findViewById(R.id.cheat_button)
        progressBar = findViewById(R.id.progressBar)
        hintButton = findViewById(R.id.hint_button)

        updateQuestion()

        questionTextView.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }

        nextButton.setOnClickListener {
            quizViewModel.nextQuestion()
            progressBar.incrementProgressBy(1)
            updateQuestion()
        }

        backButton.setOnClickListener {
            quizViewModel.prevQuestion()
            progressBar.incrementProgressBy(-1)
            updateQuestion()
        }

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        cheatButton.setOnClickListener {
            if (quizViewModel.cheatCount <= 1) {
                val i = Intent(this, CheatActivity::class.java)
                i.putExtra(ANSWER, quizViewModel.currentQuestionAnswer)
                i.putExtra(QUESTION, quizViewModel.currentQuestionText)
                resultLauncher.launch(i)
            } else {
                Toast.makeText(this, R.string.limit, Toast.LENGTH_SHORT).show()
                cheatButton.isClickable = false
            }
        }

        hintButton.setOnClickListener {
            hintButton.tooltipText = quizViewModel.currentHint
        }
    }

    private fun updateQuestion() {
        isSolved()
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
        val questionInstructor = quizViewModel.questionInstructor
        questionInstructorTextView.text = questionInstructor
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer

        val toastMsg = when {
            quizViewModel.questionBank[quizViewModel.currentIndex].isCheater -> R.string.judgement
            userAnswer == correctAnswer -> {
                R.string.correct_toast
            }
            else -> {
                R.string.incorrect_toast
            }
        }

        if (userAnswer == correctAnswer) {
            quizViewModel.questionBank[quizViewModel.currentIndex].solved = true
            trueButton.isClickable = false
            falseButton.isClickable = false
            endQuiz()
        } else {
                trueButton.isClickable = true
                falseButton.isClickable = true
            }
            Toast.makeText(this, toastMsg, Toast.LENGTH_SHORT).show()

        }

        private fun isSolved() {
            if (quizViewModel.questionBank[quizViewModel.currentIndex].solved) {
                trueButton.isClickable = false
                falseButton.isClickable = false
            } else {
                trueButton.isClickable = true
                falseButton.isClickable = true
            }
        }

    private fun endQuiz() {
        trueCount += 1
        if (trueCount == quizViewModel.questionBank.size) {
            val i = Intent(this, StartPage::class.java)
            startActivity(i)
            Toast.makeText(this, R.string.endQuiz, Toast.LENGTH_SHORT).show()
        }
    }

        override fun onSaveInstanceState(outState: Bundle) {
            super.onSaveInstanceState(outState)
            outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
            outState.putInt(KEY_INDEX, quizViewModel.currentQuestionText)
        }
    }




