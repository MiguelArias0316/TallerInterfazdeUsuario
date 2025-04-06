package com.example.miprimerapp2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {
    lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        db = DatabaseHelper(this)

        val email = findViewById<EditText>(R.id.etEmail)
        val password = findViewById<EditText>(R.id.etPassword)
        val loginBtn = findViewById<Button>(R.id.btnLogin)
        val registerLink = findViewById<TextView>(R.id.tvRegister)

        loginBtn.setOnClickListener {
            val correo = email.text.toString()
            val pass = password.text.toString()

            if (db.validarUsuario(correo, pass)) {
                val intent = Intent(this, Menu::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
            }
        }

        registerLink.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
}