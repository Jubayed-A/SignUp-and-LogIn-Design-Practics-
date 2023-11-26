package com.example.log_inandsign_up

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signup = findViewById<TextView>(R.id.dontHaveAccount)

        signup.setOnClickListener {
            val intentSignIn = Intent(this, SignUpActivity::class.java)
            startActivity(intentSignIn)
        }

    }
}