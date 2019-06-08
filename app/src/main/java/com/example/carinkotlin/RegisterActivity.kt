package com.example.carinkotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password



class RegisterActivity : AppCompatActivity() {

    lateinit var etPassword : EditText
    lateinit var etEmail : EditText
    lateinit var etFullname : EditText
    lateinit var etPhoneNumber : EditText
    lateinit var btnRegister : Button
    lateinit var btnToLogin : Button
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initilize()

    }

    private fun initilize() {

        etEmail = findViewById(R.id.daftarEmail) as EditText
        etFullname = findViewById(R.id.daftarUsername) as EditText
        etPhoneNumber = findViewById(R.id.daftarNoTelp) as EditText
        etPassword = findViewById(R.id.daftarPassword) as EditText
        btnRegister = findViewById(R.id.btnRegister) as Button
        btnToLogin = findViewById(R.id.btnToLoginActivity) as Button
        mAuth = FirebaseAuth.getInstance()
        btnToLogin.setOnClickListener{

            val loginIntent = Intent(this,LoginActivity::class.java)
            startActivity(loginIntent)

        }

        btnRegister.setOnClickListener{
            createUser()
        }

    }

    private fun createUser() {

        var password : String = etPassword.text.toString()
        var email : String = etEmail.text.toString()
        var phonenumber : String = etPhoneNumber.text.toString()
        var fullname : String = etFullname.text.toString()

        if(fullname.isNullOrEmpty())
        {
            val toast = Toast.makeText(applicationContext, "You Must Enter Your Name", Toast.LENGTH_SHORT)
            toast.show()
        }
        else if (email.isNullOrEmpty())
        {
            val toast = Toast.makeText(applicationContext, "You Must Enter Email", Toast.LENGTH_SHORT)
            toast.show()
        }
        else if (phonenumber.isNullOrEmpty())
        {
            val toast = Toast.makeText(applicationContext, "You Must Enter Phone Number", Toast.LENGTH_SHORT)
            toast.show()
        }
        else if(password.isNullOrEmpty())
        {
            val toast = Toast.makeText(applicationContext, "You Must Enter Password", Toast.LENGTH_SHORT)
            toast.show()
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                       Toast.makeText(applicationContext,"Register Successfully",Toast.LENGTH_SHORT).show()
                       val setupIntent = Intent(this,SetupActivity::class.java)
                        setupIntent.putExtra("fullname",fullname)
                        setupIntent.putExtra("phonenumber",phonenumber)
                        startActivity(setupIntent)
                        finish()

                    } else {
                        Toast.makeText(applicationContext,"Error : ",Toast.LENGTH_SHORT).show()
                    }

                }
        }

    }



}