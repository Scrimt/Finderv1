package com.example.finderv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ForgotPassord : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_passord)

        val button = findViewById<Button>(R.id.buttonReturn)
        button.setOnClickListener{
            val intent = Intent(this, Connexion::class.java)
            startActivity(intent)
        }
    }
}