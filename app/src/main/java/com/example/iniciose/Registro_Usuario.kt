package com.example.iniciose

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Registro_Usuario : AppCompatActivity() {
    private lateinit var firebaseauth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

        firebaseauth = Firebase.auth

        val txtnombre_nuevo: TextView = findViewById(R.id.edtNombre)
        val txtemail_nuevo: TextView = findViewById(R.id.edtEmailnuevo)
        val txtpassword1: TextView = findViewById(R.id.edtPasswordnuevo1)
        val txtpassword2: TextView = findViewById(R.id.edtPasswordnuevo2)
        val btnCrear: Button = findViewById(R.id.btnCrearcuenta)
        val btncrearcuenta: TextView = findViewById(R.id.Crearcuenta)

        btncrearcuenta.setOnClickListener()
        {
            val i = Intent(this, MainActivity ::class.java)
            startActivity(i)
        }

        btnCrear.setOnClickListener {
            var pass1 = txtpassword1.text.toString()
            var pass2 = txtpassword2.text.toString()
            if (pass1 == pass2) {
                createAccount(txtemail_nuevo.text.toString(), txtpassword1.text.toString())
            } else {
                Toast.makeText(
                    baseContext,
                    "Error: las contraseñas no coinciden",
                    Toast.LENGTH_SHORT
                ).show()
                txtpassword1.requestFocus()
            }
        }
    }

    private fun createAccount(email: String, password: String) {
        firebaseauth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        baseContext,
                        "Cuenta creada exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        baseContext,
                        "Error de Email o Contraseña" + task.exception,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}