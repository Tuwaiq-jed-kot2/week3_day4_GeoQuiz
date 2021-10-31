package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import kotlin.math.log
private const val KEY_INDEX = "index"
 const val  KEY_QU = "question"
 const val extra_answer_is_true_or_false = "trueOrFalse"
const val request_code = 0


class MainActivity : AppCompatActivity() {

   private lateinit var falseButton:Button
   private lateinit var trueButton: Button
   private lateinit var nextButton: Button
   private lateinit var questionTextView:TextView
   private lateinit var backButton:TextView
    private lateinit var QustionInstructerTextView :TextView
    private lateinit var cheatButton: Button
    private lateinit var appName : TextView
    private lateinit var progressBar: ProgressBar

    val TAG = "Main_Activity"
  private  val QuizViewModel by lazy {ViewModelProvider(this).get(QuizViewModel::class.java)}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode!= Activity.RESULT_OK){

            return
        }
        if (requestCode==request_code)  {
            QuizViewModel.isCheater=data?.getBooleanExtra(Extra_Answer_showen,false)?:false

        }



    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"onCreate()")
        val currentIndex = savedInstanceState?.getInt(KEY_INDEX)?:0
            val currentQuestion = savedInstanceState?.getInt(KEY_QU)?:0

                Log.d(TAG,"that is the bundle val:$currentQuestion")

           Log.d(TAG,"hi a am viewModel from()")





        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton = findViewById(R.id.next_question)
        questionTextView = findViewById(R.id.question_Tv)
        cheatButton = findViewById(R.id.cheat_Button)

        cheatButton.setOnClickListener {
            val intent = Intent(this,cheatActivity::class.java)
            intent .putExtra(extra_answer_is_true_or_false,QuizViewModel.currentuestionAnswer)
            intent.putExtra(KEY_QU,QuizViewModel.currentQuestion)
            startActivityForResult(intent, request_code)
        }

        backButton = findViewById(R.id.back_question)
        QustionInstructerTextView = findViewById(R.id.instructorName)


        falseButton.setOnClickListener {
          checkAnswer(false)
        }
        trueButton.setOnClickListener {
         checkAnswer(true)
        }
        nextButton.setOnClickListener {
            QuizViewModel.nextQuestion()
            updateQuestion()
        }

        backButton.setOnClickListener {
            QuizViewModel.nextQuestion()
            updateQuestion()
        }
    }
    fun setAnswerShowenResult(){
        val data = Intent().apply {
            putExtra(Extra_Answer_showen,true)
            setResult(request_code)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, QuizViewModel.currentIndex)
        outState.putInt(KEY_INDEX,QuizViewModel.currentQuestionText)

    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"oneStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy")
    }

    private fun updateQuestion(){

        val questionTextResId = QuizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
        val questionInstructer = QuizViewModel.instructorName
        QustionInstructerTextView.setText(questionInstructer)
    }
    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = QuizViewModel.currentuestionAnswer

        if (userAnswer == correctAnswer) {
            Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this,R.string.incorrect_toast,Toast.LENGTH_LONG).show()
        }
    }
}