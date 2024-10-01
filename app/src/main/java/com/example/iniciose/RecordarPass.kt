package com.example.iniciose

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RecordarPass : AppCompatActivity() {
    private lateinit var firebaseauth: FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recordar_pass)
        val txtmail : TextView = findViewById(R.id.txtEmailCambio)
        val btnCambiar : Button = findViewById(R.id.btnCambiar)
        val btnInicio : TextView = findViewById(R.id.btnInicio_restablecer)

        btnCambiar.setOnClickListener()
        {
            sendPasswordReset(txtmail.text.toString())
        }

        btnInicio.setOnClickListener()
        {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        firebaseauth= Firebase.auth
    }
    private fun sendPasswordReset(email: String)
    {
        firebaseauth.sendPasswordResetEmail(email).addOnCompleteListener()
        {
          task ->
            if (task.isSuccessful)
            {
            Toast.makeText(baseContext,"Correo enviado exitosamente", Toast.LENGTH_SHORT).show()
            }
          else
          {
              Toast.makeText(baseContext,"Error, no se logro completar el proceso", Toast.LENGTH_SHORT).show()
          }
        }
    }
}