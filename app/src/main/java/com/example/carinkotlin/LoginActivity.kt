package com.example.carinkotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    lateinit var  etEmail : EditText
    lateinit var  etPassword : EditText
    lateinit var  btnToRegister : Button
    lateinit var  btnLogin : Button
    lateinit var  mAuth : FirebaseAuth

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
        mAuth = FirebaseAuth.getInstance()
        btnToRegister.setOnClickListener{
            val registerIntent = Intent(this,RegisterActivity::class.java)
            startActivity(registerIntent)
        }
        btnLogin.setOnClickListener {
            signInUser()
        }

    }

    private fun signInUser()
    {
        val email : String = etEmail.text.toString()
        val password : String = etPassword.text.toString()

        if(email.isNullOrEmpty())
        {
            Toast.makeText(applicationContext,"Enter your email ",Toast.LENGTH_SHORT).show()
        }
        else if (password.isNullOrEmpty())
        {
            Toast.makeText(applicationContext,"Enter Your Password",Toast.LENGTH_SHORT).show()
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(applicationContext,"Login Success",Toast.LENGTH_SHORT).show()
                        val homeIntent = Intent(this,MainActivity::class.java)
                        startActivity(homeIntent)
                        finish()
                    } else {
                        Toast.makeText(applicationContext,"Login Failed",Toast.LENGTH_SHORT).show()
                    }

                })
        }
    }
}