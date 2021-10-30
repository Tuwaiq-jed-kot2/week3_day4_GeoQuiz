package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider


const val EXTERA_TRUE_FALSE="answer!!"
const val EXTERA_QUESTION="question"
private const val REQUEST_CODE_CHEATING=0
private const val KEY_INDEX="index"

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var preButton: Button
    private lateinit var cheatBtn: Button

    private val QviewModel by lazy { ViewModelProvider(this).get(QviewModel::class.java) }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode!= Activity.RESULT_OK){
            return
        }
        if (requestCode== REQUEST_CODE_CHEATING){
            QviewModel.isCheater= data?.getBooleanExtra(EXTRA_ANSWER_SHOWEN,false)?:false
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init_views()



        trueButton.setOnClickListener {
            checkAnswer(true)

        }
        falseButton.setOnClickListener {
            checkAnswer(false)

        }
        nextButton.setOnClickListener {
            QviewModel.nextQuestion()
            updateQ()
        }
        preButton.setOnClickListener {
            QviewModel.prevQuestion()
            updateQ()
        }
        cheatBtn.setOnClickListener {
            val intent = Intent(this, CheatActivity2::class.java).apply {
                putExtra(EXTERA_TRUE_FALSE, QviewModel.currentQuestionsAnswer)
                putExtra(EXTERA_QUESTION, QviewModel.currentQuestioText)
                startActivityForResult(intent, REQUEST_CODE_CHEATING)
            }
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, QviewModel.currentIndex)
    }

    private fun updateQ() {
        val qId = QviewModel.currentQuestioText
        textView.setText(qId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val currectAnswer = QviewModel.currentQuestionsAnswer
        val theMsg = when {
            QviewModel.isCheater ->R.string.cheater_toast
            userAnswer == currectAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }
        Toast.makeText(this, theMsg, Toast.LENGTH_LONG).show()
    }

    private fun init_views() {
        trueButton = findViewById(R.id.trueB)
        falseButton = findViewById(R.id.falseB)
        nextButton = findViewById(R.id.nextB)
        preButton = findViewById(R.id.prevB)
        cheatBtn = findViewById(R.id.cheatBTN)
        textView = findViewById(R.id.textViewQ)
    }
}
