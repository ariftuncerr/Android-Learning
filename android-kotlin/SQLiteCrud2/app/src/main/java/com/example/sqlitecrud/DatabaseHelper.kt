package com.example.sqlitecrud

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper (context : Context, dbName : String, version : Int) : SQLiteOpenHelper(context,dbName,null,version) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS users(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, email TEXT UNIQUE, password TEXT)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }
    fun readUsers() : List<UserModel>{
        val db = readableDatabase
        val usersList = mutableListOf<UserModel>()
        try {
            val query = "SELECT * FROM users"
            val cursor = db.rawQuery(query,null)
            while (cursor.moveToNext()){
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val email = cursor.getString(2)
                val user = UserModel(id,name,email)
                usersList.add(user)
            }
        }
        catch (e : Exception){
            Log.e("Database","Read error ${e.localizedMessage}")
        }
        db.close()
        return usersList
    }
    fun insertUser(name: String, email : String, password: String) : Boolean{
        val db = writableDatabase
        try {
            //val query = "INSERT INTO users (name,email,password) VALUES ($name,$email,$password)"
            //db.execSQL(query)
            val values = ContentValues().apply {
                put("name",name)
                put("email",email)
                put("password",password)
            }
            db.insert("users",null,values)
            db.close()
            return true
        }
        catch (e : Exception){
            db.close()
            Log.e("Database","Insert error ${e.localizedMessage}")
            return false
        }


    }
    fun updateUser(email : String,newName: String, newPassword: String) : Boolean {
        val db = writableDatabase
        try {
            //val query = "INSERT INTO users (name,email,password) VALUES ($name,$email,$password)"
            //db.execSQL(query)
            val values = ContentValues().apply {
                put("name",newName)
                put("password",newPassword)
            }
            val result = db.update("users",values,"email=?", arrayOf(email))
            if(result > 0)
                return true
            else
                return false
        }
        catch (e : Exception){
            Log.e("Database","Update error ${e.localizedMessage}")
            return false
        }
        db.close()

    }
    fun deleteUser(email: String): Boolean {
        val db = writableDatabase
        try {
            val result = db.delete("users","email=?",arrayOf(email))
            if(result > 0){
                println("Result:"+result)
                return true
            }
            else
                return false
            return true
        }
        catch (e : Exception){
            Log.e("Database","Delete error ${e.localizedMessage}")
            return false
        }
        db.close()
    }



}