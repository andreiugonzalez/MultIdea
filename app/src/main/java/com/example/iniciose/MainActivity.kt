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


class MainActivity : AppCompatActivity() {
    private lateinit var firebaseauth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val btningresar: Button = findViewById(R.id.btnIngresar)
        val txtemail: TextView = findViewById(R.id.edtEmail)
        val txtpass: TextView = findViewById(R.id.edtPassword)
        val btnCrear_CuentaNueva = findViewById<TextView>(R.id.Crearcuenta)
        val btnRecordar : TextView = findViewById(R.id.btnOlvidar)
        firebaseauth = Firebase.auth
        btningresar.setOnClickListener()
        {
         signIn(txtemail.text.toString(),txtpass.text.toString())

        }
        btnCrear_CuentaNueva.setOnClickListener()
        {
            val i = Intent(this, Registro_Usuario::class.java)
            startActivity(i)
        }
        btnRecordar.setOnClickListener()
        {
            val i = Intent(this, RecordarPass::class.java)
            startActivity(i)
        }

    }

    private fun signIn(email: String, password: String)
    {
     firebaseauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){
         task -> if (task.isSuccessful) {
             val user = firebaseauth.currentUser
             Toast.makeText(baseContext,user?.uid.toString(), Toast.LENGTH_SHORT).show()
             //aqui vamos a ir a la siguiente pantalla Home
            val i = Intent(this,Home::class.java)
            startActivity(i)

     }
         else
         {
             Toast.makeText(baseContext,"Error de Email o Contraseña", Toast.LENGTH_SHORT).show()
         }
     }
    }
}