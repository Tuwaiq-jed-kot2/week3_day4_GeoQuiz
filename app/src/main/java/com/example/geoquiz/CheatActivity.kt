package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

 const val EXTRA_ANSWER_SHOWN = "a"
 const val CHEAT_COUNT = "b"
var count = 0

class CheatActivity : AppCompatActivity() {

    private lateinit var showAnswerButton: Button
    private lateinit var cheatAnswerTextView: TextView
    private lateinit var cheatQTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        showAnswerButton = findViewById(R.id.show_answer)
        cheatAnswerTextView = findViewById(R.id.cheatAnswer_Tv)
        cheatQTextView = findViewById(R.id.cheatQ_Tv)

        val cheatedQ = intent.getIntExtra(QUESTION, 0)
        cheatQTextView.setText(cheatedQ)

        showAnswerButton.setOnClickListener {
            count += 1
            val cheated = intent.getBooleanExtra(ANSWER, false)
            cheatAnswerTextView.text = cheated.toString()

            setAnswerShownResult()
            }
        }

    private fun setAnswerShownResult() {

        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, true)
            putExtra(CHEAT_COUNT, count)
        }
        setResult(Activity.RESULT_OK, data)
    }
}