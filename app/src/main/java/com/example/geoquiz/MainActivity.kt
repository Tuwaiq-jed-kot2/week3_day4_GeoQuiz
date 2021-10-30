package com.example.geoquiz
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalStateException
import kotlin.math.log
import java.time.Instant as intant


private const val KEY_INDEX="index"
private const val requestCodeCheat =123
const val Qtocheat="jkjkjkj"
class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var backButton: Button
    private lateinit var questinTextView: TextView
    private lateinit var cheatButton: Button


    private var currevtIndex = 0

    val TAG = "main_Activty"
    private val quizViewModelv by lazy { ViewModelProvider(this).get(QuizViewModel::class.java) }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK) {
            return
        }
        if (resultCode == requestCodeCheat) {
        quizViewModelv.isCheater =
        data?.getBooleanExtra(extrx_cheater, false) ?: false
        }
    }

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate()")

        val currentIdex = savedInstanceState?.getInt(KEY_INDEX) ?: 0
        quizViewModelv.currevtIndex = currevtIndex

        init2()

        cheatButton.setOnClickListener {
            val Intant = Intent(this, cheat_activity::class.java)
            Intant.putExtra(ExtraAnsweerOrNOT, quizViewModelv.currentQuestionAnswer)
            startActivity(Intant)
            startActivityForResult(Intant, requestCodeCheat)
        }



        trueButton.setOnClickListener {

            checkAnsewr(true)
        }
        falseButton.setOnClickListener {
            checkAnsewr(false)
        }
        nextButton.setOnClickListener {
            quizViewModelv.nextQ()
            ubdateQuestion()
        }
        questinTextView.setOnClickListener {
            quizViewModelv.nextQ()
            ubdateQuestion()
        }
        backButton.setOnClickListener {
            quizViewModelv.backQ()
            restoreQuestion()

        }

        ubdateQuestion()
    }

    private fun init2() {
        questinTextView = findViewById(R.id.next_question)
        questinTextView = findViewById(R.id.back_quetion)
        trueButton = findViewById(R.id.true_Botton)
        falseButton = findViewById(R.id.false_Button)
        nextButton = findViewById(R.id.next_question)
        questinTextView = findViewById(R.id.question_textVeiw)
        backButton = findViewById(R.id.back_quetion)
        questinTextView = findViewById(R.id.question_textVeiw)
        cheatButton = findViewById(R.id.cheat_button)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, quizViewModelv.currevtIndex)
        outState.putInt(KEY_INDEX, quizViewModelv.currntQuestionText)
    }

    private fun ubdateQuestion() {
        val questionTextResId = quizViewModelv.currntQuestionText
        questinTextView.setText(questionTextResId)
    }

    private fun restoreQuestion() {
        val questionTextResId = quizViewModelv.currntQuestionText
        questinTextView.setText(questionTextResId)
    }

    private fun checkAnsewr(userAnswer: Boolean) {
        val correctAnswer = quizViewModelv.currentQuestionAnswer
        val toastMassege = when {
            quizViewModelv.isCheater -> R.string.judjment_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }
        Toast.makeText(this, toastMassege, Toast.LENGTH_LONG)
            .show()

    }
}