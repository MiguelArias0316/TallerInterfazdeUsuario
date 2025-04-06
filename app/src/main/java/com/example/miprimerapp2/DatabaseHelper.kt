package com.example.miprimerapp2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "usuarios.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, correo TEXT, contraseña TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }

    fun insertarUsuario(correo: String, contraseña: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("correo", correo)
            put("contraseña", contraseña)
        }
        val result = db.insert("usuarios", null, values)
        return result != -1L
    }

    fun validarUsuario(correo: String, contraseña: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM usuarios WHERE correo=? AND contraseña=?",
            arrayOf(correo, contraseña)
        )
        val valido = cursor.count > 0
        cursor.close()
        return valido
    }
}