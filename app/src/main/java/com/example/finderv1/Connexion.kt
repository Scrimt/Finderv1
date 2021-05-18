package com.example.finderv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Connexion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connexion)

        val button = findViewById<Button>(R.id.buttonforgot)
        button.setOnClickListener{
            val intent = Intent(this, ForgotPassord::class.java)
            startActivity(intent)
        }

        val button1 = findViewById<Button>(R.id.buttoncreate)
        button1.setOnClickListener{
            val intent = Intent(this, AccountCreation::class.java)
            startActivity(intent)
        }
    }
}