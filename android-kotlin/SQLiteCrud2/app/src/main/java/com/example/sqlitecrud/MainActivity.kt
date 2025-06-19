package com.example.sqlitecrud

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlitecrud.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dbName : String = "UsersDatabase"
    private lateinit var db : DatabaseHelper
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
        db = DatabaseHelper(this,dbName,1)


    }
    fun onSaveClick(view : View){
        val email = binding.emailTxt.text.toString()
        val name = binding.nameEditTxt.text.toString()
        val password = binding.passwordEditTxt.text.toString()

       if (email.isEmpty() || name.isEmpty() || password.isEmpty())
            Snackbar.make(view,"You should fill in the blanks",Snackbar.LENGTH_SHORT).show()
        else{
           val result = db.insertUser(name,email,password)
           Snackbar.make(view,"Users successfully added",Snackbar.LENGTH_SHORT).show()

           println(result)
       }

    }
    fun onRecordClick(view : View){
        val userList = db.readUsers()
        val userListAdapter = UserAdapter(this,userList)
        println(userList)

        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.adapter = userListAdapter
    }
    fun onDeleteClick(view : View){
        val email = binding.emailTxt.text.toString()

        println(email)
        val isDeleted = db.deleteUser(email)
        if (isDeleted)
            Toast.makeText(applicationContext,"Deleted", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(applicationContext,"was not deleted", Toast.LENGTH_SHORT).show()

    }
    fun onUpdateClick(view: View){
        val email = binding.emailTxt.text.toString()
        val newName = binding.nameEditTxt.text.toString()
        val newPassword = binding.passwordEditTxt.text.toString()
        val isUpdated = db.updateUser(email,newName,newPassword)
        if (isUpdated)
            Toast.makeText(applicationContext,"Updated", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(applicationContext,"was not updated", Toast.LENGTH_SHORT).show()
    }


}