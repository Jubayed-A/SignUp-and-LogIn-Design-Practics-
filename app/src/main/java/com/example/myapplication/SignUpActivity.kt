package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()



        binding.btnSignIn.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
            finish()
        }

        binding.apply {
            binding.btnSignUp.setOnClickListener {
                val name = name.text.toString()
                val email = email.text.toString()
                val password = Password.text.toString()
                val rePassword = rePassword.text.toString()

                if (name.isEmpty() && email.isEmpty() && password.isEmpty() && rePassword.isEmpty()) {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Please File the all filed",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (password != rePassword) {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Password and RePassword Must be Same",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    this@SignUpActivity,
                                    "Registration is Successful",
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(
                                    Intent(
                                        this@SignUpActivity,
                                        LogInActivity::class.java
                                    )
                                )
                                finish()
                            } else {
                                Toast.makeText(
                                    this@SignUpActivity,
                                    "Registration Failed : ${task.exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
        }
    }
}