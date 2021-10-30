package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CheatActivity : AppCompatActivity() {
    private lateinit var tvquestion:TextView
    private lateinit var tvanswer:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        tvquestion = findViewById(R.id.tvquestion)
        tvanswer = findViewById(R.id.tvanswer)
        var answer =  intent.extras?.getString("key")

        var question = intent.extras?.getString("keyQuestion")

        tvanswer.text = answer.toString()
        tvquestion.text = question.toString()

    }

}