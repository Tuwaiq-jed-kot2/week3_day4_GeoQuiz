package com.example.geoquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var falseButton: Button
    private lateinit var trueButton: Button
    private lateinit var textView2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView2 = findViewById(R.id.textView2)
        var quesTion = textView2.text.toString()
        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)

        falseButton.setOnClickListener {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG).show()
            var intentFalse = Intent(this, CheatActivity::class.java)
            intentFalse.putExtra("key", "your answer is correct the capitl of KSA is Riyadh")
            intentFalse.putExtra("keyQuestion",quesTion)
            startActivity(intentFalse)
        }

        trueButton.setOnClickListener {
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_LONG).show()
            var intentTrue = Intent(this, CheatActivity::class.java)
            intentTrue.putExtra("key", "your answer is incorrect the capitl of KSA is Riyadh")
            intentTrue.putExtra("keyQuestion",quesTion)
            startActivity(intentTrue)
        }

    }
}