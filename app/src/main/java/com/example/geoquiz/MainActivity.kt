package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import java.lang.IllegalStateException

private const val KYE_INDEX = "index"
 const val KYE_QUESTION = "question"
private const val KYE_WRITER = "writer"
 const val EXTRA_ANSWER_IS_TRUE_OR_NOT="is.it.true?"
private const val REQUEST_CODE_CHEAT = 0

class MainActivity : AppCompatActivity() {

    private lateinit var falseButton:Button
    private lateinit var trueButton:Button
    private lateinit var nextButton:Button
    private lateinit var questionTextView:TextView

    private lateinit var backButton:Button
    private lateinit var textView: TextView
    private lateinit var cheatButton:Button






    private val TAG = "Main_Activity"

    private val QuizViewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java) }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG,"the result code is : $requestCode")

        if (resultCode!=Activity.RESULT_OK){
            return
        }
        if (requestCode== REQUEST_CODE_CHEAT){

            QuizViewModel.isCheater=data?.getBooleanExtra(EXTRA_ANSWER_SHOWN,false)?:false
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG,"onCreate()")
        val currentIndex = savedInstanceState?.getInt(KYE_INDEX) ?: 0
        Log.d(TAG,"bundle val : $currentIndex")
        QuizViewModel.currentIndex = currentIndex
        val currentQuestion = savedInstanceState?.getInt(KYE_QUESTION) ?: 0
        Log.d(TAG,"hello! here is the bundle val for current question: $currentQuestion")
     //  val currentQuestionWriter = QuizViewModel?.getS(textView) ?:0
       Log.d(TAG,"the writer is:")
      //  textView.setText(currentQuestionWriter)




        Log.d(TAG,"hey i'm view model from main activity $QuizViewModel")





        falseButton=findViewById(R.id.false_button)
        trueButton=findViewById(R.id.true_button)
        nextButton=findViewById(R.id.next_question)
        questionTextView=findViewById(R.id.question_Tv)
        backButton=findViewById(R.id.back_Button)
        textView=findViewById(R.id.text_ViewWriter)
        cheatButton=findViewById(R.id.cheatButton)

        cheatButton.setOnClickListener(){
            val intent = Intent(this,cheatActivity::class.java)
            intent.putExtra(EXTRA_ANSWER_IS_TRUE_OR_NOT,QuizViewModel.currentQuestionAnswer)
            intent.putExtra(KYE_QUESTION,QuizViewModel.currentQuestionText)
            startActivityForResult(intent, REQUEST_CODE_CHEAT)

        }








        falseButton.setOnClickListener {

            checkAnswer(false)

        }


        trueButton.setOnClickListener {
           checkAnswer(true)
        }


        nextButton.setOnClickListener {
            if (QuizViewModel.currentQuestionCheckAnswer){
                trueButton.isClickable =true
                falseButton.isClickable =true


            }

            QuizViewModel.nextQuestion()

            updateQuestion()

        }
        questionTextView.setOnClickListener {

            updateQuestion()
        }
        backButton.setOnClickListener {
            if (QuizViewModel.currentQuestionCheckAnswer){
                trueButton.isClickable =true
                falseButton.isClickable =true

            }
            QuizViewModel.backQuestion()
            updateQuestion()
        }




        updateQuestion()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KYE_INDEX,QuizViewModel.currentIndex)
        outState.putInt(KYE_INDEX,QuizViewModel.currentQuestionText)

    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"onRestart()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy()")
    }

    private fun updateQuestion(){
        val questionTextResId = QuizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
        val questionWriter = QuizViewModel.questions
        textView.setText(questionWriter)

    }
    private fun checkAnswer(userAnswer:Boolean){

        val correctAnswer = QuizViewModel.currentQuestionAnswer




        val toastMassage = when{
            QuizViewModel.isCheater -> R.string.jugementToast
            userAnswer==correctAnswer->R.string.correct_toast
            else->R.string.incorrect_toast
        }

        Toast.makeText(this,toastMassage,Toast.LENGTH_LONG).show()

        if (userAnswer == correctAnswer){
            QuizViewModel.currentQuestionCheckAnswer = true
            trueButton.isClickable =false
            falseButton.isClickable =false



           val toast = Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_LONG)
           toast.setGravity(Gravity.TOP,0,300)
            toast.show()
        }else{
           val toast =Toast.makeText(this,R.string.incorrect_toast,Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP,0,300)
            toast.show()
        }
    }

}