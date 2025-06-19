package com.example.sqlite

import android.app.AlertDialog
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sqlite.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var database : SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        createDatabase()

    }
    fun onGetRecordClick(view: View){
        val gridList = mutableListOf<String>()
        try {
            val query = "SELECT * from users"
            val cursor = database.rawQuery(query,null)

            val nameIndex = cursor.getColumnIndex("userName")
            val passIndex = cursor.getColumnIndex("password")

            while (cursor.moveToNext()){
                gridList.add("Name: ${cursor.getString(nameIndex)} Pass: ${cursor.getString(passIndex)}")
            }

        }
        catch (e : kotlin.Exception){
            println(e.localizedMessage)
        }
        val recordAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,gridList)
        binding.gridView.adapter = recordAdapter
    }
    fun createDatabase(){
        try {
            database = this.openOrCreateDatabase("Users",MODE_PRIVATE,null)
            database.execSQL("CREATE TABLE IF NOT EXISTS users(userName VARCHAR,password VARCHAR)")

        }
        catch (e : kotlin.Exception){
            println("Create Database Error"+e.localizedMessage)
        }
    }

    fun onSaveClick(view : View){
        val username = binding.userNameEditTxt.text.toString()
        val password = binding.userpassEditTxt.text.toString()
        if (username.isEmpty()|| password.isEmpty())
            Snackbar.make(view,"Please enter username and password", Snackbar.LENGTH_SHORT).show()
        else{
            try {

                val insertQuery = "INSERT INTO users (userName,password) VALUES('$username','$password')"
                AlertDialog.Builder(this)
                    .setTitle("User Registration")
                    .setMessage("Are you sure?")
                    .setPositiveButton ("YES"){ dialog,_ ->
                        database.execSQL(insertQuery)
                        Snackbar.make(view,"user succesfully added", Snackbar.LENGTH_SHORT).show()
                    }
                    .setNegativeButton ("No") { dialog,_->
                        dialog.dismiss()
                    }
                    .show()

            }
            catch (e: Exception){
                println("insert error"+e.localizedMessage)
            }
        }


    }
}