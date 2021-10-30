package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.service.voice.VoiceInteractionSession
import android.util.Log
import android.view.Gravity
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalStateException

private const val  KEY_INDEX="index"
 const val  Question_Bankch="0"
 const val  Extra_ANSWER_IS_TRUE="MainActivity.answerIsTrue"
 private val REQUEST_CODE_CHEAT=999


class MainActivity : AppCompatActivity() {
    //decalar
    private lateinit var falseButton: Button
    private lateinit var trueButton: Button
    private lateinit var nextButtom: Button
    private lateinit var textView: TextView
    private lateinit var backButton: Button
    private lateinit var consText: TextView
    private lateinit var cheatButton: Button


    private val questionBank = listOf(
        Question(R.string.first_question, true, "Ali"),
        Question(R.string.second_question, false, "Ahmed"),
        Question(R.string.thrid_question, false, "Roya "),


        )

    private var currentIndex = 0


    val TAG = "Main_Activity"
    private val quizViewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java) }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
    if(requestCode== REQUEST_CODE_CHEAT)
    {
        quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_ANSWER_SHOWN,false)?:false


    }
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)


         Log.d(TAG,"onCreate()")
        val currentIndex=savedInstanceState?.getInt(KEY_INDEX)?:0
        quizViewModel.currentIndex= currentIndex
        Log.d(TAG,"baunle val:$currentIndex")
        quizViewModel.currentIndex=currentIndex

        Log.d(TAG,"baunle hi here is the boundle val for current question:$currentIndex")
        quizViewModel.currentIndex=currentIndex


        Log.d(TAG,"hi iam view Modle from main Activity$quizViewModel")




         falseButton=findViewById(R.id.falseButton)
        trueButton=findViewById(R.id.trueButton)
        nextButtom=findViewById(R.id.next_question)
        textView=findViewById(R.id.qestios_Tv)
        backButton=findViewById(R.id.back_bottom)
         consText=findViewById(R.id.con_text)
        cheatButton=findViewById(R.id.cheat_button)
          falseButton.isClickable
        (false)
          trueButton.isClickable
        (true)

       cheatButton.setOnClickListener {
           val intent=Intent(this,cheatActivity::class.java)
           intent.putExtra(Question_Bankch,quizViewModel.cunrrentQuestionText)
           intent.putExtra(Extra_ANSWER_IS_TRUE,quizViewModel.cunrrentQuestionAnswer)
           startActivityForResult(intent, REQUEST_CODE_CHEAT)
       }



        falseButton.setOnClickListener {
/*
             val X= Toast.makeText(this,R.string.incorrect_toast,Toast.LENGTH_LONG);
            X.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL,20,20)
            X.show()

 */
           checkAnswer(false)
        }

        trueButton.setOnClickListener {
            /*
            val Y= Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_LONG);

            Y.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL,20,20)

            Y.show()

             */
            checkAnswer(true)
        }
                /*
        .setOnClickListener {
            quizViewModel.currentIndex=(currentIndex+1)%questionBank.size
            updateQuestion()
        }

                 */

        nextButtom.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }
        /*
        backButton.setOnClickListener {
            currentIndex=(currentIndex-1)%questionBank.size

            updateQuestion()
        }

         */

        updateQuestion()

         }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX,quizViewModel.currentIndex)
        outState.putInt(KEY_INDEX,quizViewModel.cunrrentQuestionText)
    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG,"ON START()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"onRestart()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResum")

    }





    private fun updateQuestion(){

            val questionTextResId = quizViewModel.cunrrentQuestionText
            textView.setText(questionTextResId)
           val constrctorQestion=quizViewModel.qestionConstrctor
           consText.setText(constrctorQestion)
        }


    private fun  checkAnswer(userAnswer:Boolean){
        Log.d(TAG,"im from  checkAnswer",IllegalStateException())
        val correctAnswer=quizViewModel.cunrrentQuestionAnswer
           val toastMassage=when{
               quizViewModel.isCheater->R.string.judgement
               userAnswer==correctAnswer->R.string.correct_toast
               else->R.string.incorrect_toast

           }
        Toast.makeText(this,toastMassage,Toast.LENGTH_LONG).show()
        /*
        if (correctAnswer==userAnswer){
            Toast.makeText(this,"correct !!",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this," incorrect !!",Toast.LENGTH_LONG).show()
        }

         */
    }

    }
