package com.example.finderv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase

class Auth : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    private lateinit var refUsers : DatabaseReference
    private var firebaseUserID: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val button1 = findViewById<Button>(R.id.buttoncreate)
        button1.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
        mAuth = FirebaseAuth.getInstance()

        val button_log = findViewById<ImageButton>(R.id.login_btn)
        button_log.setOnClickListener {
            loginUser()
        }
    }



    private fun loginUser()
    {
        val pass = findViewById<EditText>(R.id.Password_Log)
        val ema = findViewById<EditText>(R.id.Email_log)

        val email: String = ema.text.toString()
        val password: String = pass.text.toString()

        if (email == "")
        {
            Toast.makeText(this@Auth,"Ecrire un email", Toast.LENGTH_LONG).show()
        }
        else if (password == "")
        {
            Toast.makeText(this@Auth,"Ecrire un mot de passe", Toast.LENGTH_LONG).show()
        }
        else{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener{task ->
                        if (task.isSuccessful)
                        {
                            val intent = Intent(this@Auth, Home::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()

                        }
                        else
                        {
                            Toast.makeText(this@Auth,"Erreur message" + task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
        }
    }
}
