package com.example.newgeoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.R
import androidx.lifecycle.ViewModelProvider

private const val KEY_INDEX = "Index"
private const val REQUEST_CODE_CHEAT = 0
const val questionTOCheatActivity = "plzWork"
const val EXTRA_W_T_or_f = "trueOrFalse"



class MainActivity : AppCompatActivity() {

// day 30 oct
    private lateinit var trueBtn:Button
    private lateinit var falseBtn:Button
    private lateinit var nextBtn:Button
    private lateinit var preBtn:Button
    private lateinit var questionTV:TextView
    private lateinit var cheatBtn:Button


    val quizViewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java) }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode!= Activity.RESULT_OK) return
        if (requestCode== REQUEST_CODE_CHEAT)quizViewModel.isCheater=data?.getBooleanExtra(EXTRA_ANSWER_SHOW,false)?:false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currntinde=savedInstanceState?.getInt(KEY_INDEX)?:1
        setContentView(R.layout.activity_main)
        questionTV=findViewById(R.id.question_tv)
        intiViews()
      trueBtn.setOnClickListener{
          checkAnswer(true)

      }
        falseBtn.setOnClickListener{
            checkAnswer(false)
        }
        nextBtn.setOnClickListener{
            quizViewModel.nextQuestion()
            updateQuestion()

        }
        preBtn.setOnClickListener {
            quizViewModel.preQuestion()
            updateQuestion()
        }

        cheatBtn.setOnClickListener {
            val intent = Intent(this , Cheat_activity::class.java)
            intent.putExtra(EXTRA_W_T_or_f,quizViewModel.currentAnswer)
            intent.putExtra(questionTOCheatActivity,quizViewModel.currentAnswer)
            startActivityForResult(intent, REQUEST_CODE_CHEAT)
        }



    }



    private fun updateQuestion(){
        val questionResId=quizViewModel.currentQuestion
        questionTV.setText(questionResId)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt(KEY_INDEX,quizViewModel.currentIndex)
        outState.putInt(KEY_INDEX,quizViewModel.currentQuestion)
    }


    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer= quizViewModel.currentAnswer
        if (userAnswer==correctAnswer){
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()
        }else{Toast.makeText(this,R.string.incorrect_toast,Toast.LENGTH_SHORT).show()}
      //


    }

    private fun intiViews() {
        questionTV=findViewById(R.id.question_tv)
        trueBtn = findViewById(R.id.true_btn)
        falseBtn = findViewById(R.id.false_btn)
        nextBtn = findViewById(R.id.next_btn)
        cheatBtn = findViewById(R.id.cheat_button)
        questionTV = findViewById(R.id.question_tv)
        preBtn = findViewById(R.id.pre_btn)

    }


    }
