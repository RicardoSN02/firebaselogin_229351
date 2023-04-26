package com.example.practica13

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.practica13.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.signInAppCompatButton.setOnClickListener{
            val mEmail=binding.emailEditText.text.toString()
            val mPassword=binding.passwordEditText.text.toString()

            when{
                mEmail.isEmpty() || mPassword.isEmpty()->{
                    Toast.makeText(baseContext, "Mail o contraseña incorrecta.",
                     Toast.LENGTH_SHORT).show()
                }else ->{
                    SignIn(mEmail,mPassword)
                }
            }
        }
    }

    private fun SignIn(email:String,password:String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    Log.d("TAG","signInWithEmail:success")
                    //val user= auth.currentUser
                    //updateUI(user)
                    reaload()
                }else{
                    Log.w("TAG","signInWithEmail:failure",task.exception)
                    Toast.makeText(baseContext,"Authentication failed.",
                    Toast.LENGTH_SHORT).show()
                    //UpdateUI(null)
                }
            }


    }

    private fun reaload(){
        val intent= Intent(this,MainActivity::class.java)
        this.startActivity(intent)
    }


}