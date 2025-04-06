package com.example.miprimerapp2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Register : AppCompatActivity() {
    lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        db = DatabaseHelper(this)

        val email = findViewById<EditText>(R.id.etNewEmail)
        val password = findViewById<EditText>(R.id.etNewPassword)
        val registerBtn = findViewById<Button>(R.id.btnRegister)

        registerBtn.setOnClickListener {
            val correo = email.text.toString()
            val pass = password.text.toString()

            if (db.insertarUsuario(correo, pass)) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}