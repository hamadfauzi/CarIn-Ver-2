package com.example.carinkotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {

    lateinit var  etEmail : EditText
    lateinit var  etPassword : EditText
    lateinit var  btnToRegister : Button
    lateinit var  btnLogin : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initilize()
    }

    private fun initilize()
    {
        etEmail = findViewById(R.id.inputEmail) as EditText
        etPassword = findViewById(R.id.inputPassword) as EditText
        btnToRegister = findViewById(R.id.btnToRegisterActivity) as Button
        btnLogin = findViewById(R.id.btnLogin) as Button

        btnToRegister.setOnClickListener{
            val registerIntent = Intent(this,RegisterActivity::class.java)
            startActivity(registerIntent)
        }
    }
}