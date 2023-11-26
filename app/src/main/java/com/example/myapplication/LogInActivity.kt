package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val signUp = findViewById<Button>(R.id.btnSigup)

        signUp.setOnClickListener {
            val intentSigUp = Intent(this, SignUpActivity::class.java)
            startActivity(intentSigUp)
        }



    }
}