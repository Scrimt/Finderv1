package com.example.finderv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AccountCreation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_creation)

        val button = findViewById<Button>(R.id.buttoncreation)
        button.setOnClickListener{
            val intent = Intent(this, Questionnaire::class.java)
            startActivity(intent)
        }
    }
}