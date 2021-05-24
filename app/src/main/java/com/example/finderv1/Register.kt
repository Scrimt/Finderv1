package com.example.finderv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    private lateinit var refUsers : DatabaseReference
    private var firebaseUserID: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()
        val button = findViewById<Button>(R.id.creationbtn1)
        button.setOnClickListener{
            registerUser()
        }

        val button3 = findViewById<ImageButton>(R.id.ReturnButton1)
        button.setOnClickListener{
            val intent = Intent(this, Auth::class.java)
            startActivity(intent)
        }
    }

    private fun registerUser()
    {
        val userN = findViewById<EditText>(R.id.username_register)
        val ema = findViewById<EditText>(R.id.email_register)
        val pass = findViewById<EditText>(R.id.password_register)

        val username: String = userN.text.toString()
        val email: String = ema.text.toString()
        val password: String = pass.text.toString()

        if (username == "")
        {
            Toast.makeText(this@Register,"Ecrire un username", Toast.LENGTH_LONG).show()
        }
        else if (email == "")
        {
            Toast.makeText(this@Register,"Ecrire un email", Toast.LENGTH_LONG).show()
        }
        else if (password == "")
        {
            Toast.makeText(this@Register,"Ecrire un mot de passe", Toast.LENGTH_LONG).show()
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener{ task ->
                    if(task.isSuccessful)
                    {
                        firebaseUserID = mAuth.currentUser!!.uid
                        refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserID)

                        val userHashMap = HashMap<String,Any>()
                        userHashMap["uid"] = firebaseUserID
                        userHashMap["username"] = username
                        userHashMap["profile"] = "https://firebasestorage.googleapis.com/v0/b/finderv1-e2f15.appspot.com/o/Profile1.jpg?alt=media&token=621cb584-938a-45f8-83c5-7bca2c51045d"
                        userHashMap["cover"] = "https://firebasestorage.googleapis.com/v0/b/finderv1-e2f15.appspot.com/o/Cover.jpg?alt=media&token=8d39220e-4ac9-47c1-a13c-a34f0c3a0466"
                        userHashMap["status"] = "offline"

                        refUsers.updateChildren(userHashMap)
                                .addOnCompleteListener{task ->
                                    if (task.isSuccessful)
                                    {
                                        val intent = Intent(this@Register, Auth::class.java)
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                        startActivity(intent)
                                        finish()
                                    }
                                }

                    }
                    else
                    {
                        Toast.makeText(this@Register,"Erreur message" + task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                    }

                }
        }
    }
}
