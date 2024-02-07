package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.databinding.ActivityLogInBinding
import com.example.myapplication.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }

        binding.apply {
            btnLogIn.setOnClickListener {
                val email = logInEmail.text.toString()
                val password = logInPassword.text.toString()

                if (email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(
                        this@LogInActivity,
                        "Please fill the all filed",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this@LogInActivity, MainActivity::class.java))
                            finish()
                            Toast.makeText(
                                this@LogInActivity,
                                "Login Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this@LogInActivity,
                                "LogIn Failed : ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    // check if user already exit or not
    override fun onStart() {
        super.onStart()
        // if user already login then don't login again
        val currentUser: FirebaseUser? = auth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}