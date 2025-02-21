package com.example.kotlin3

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var myTextView: TextView
    private lateinit var myNameText: EditText
    private lateinit var myAgeText: EditText
    private lateinit var myJobText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        myTextView = findViewById(R.id.textView)
        myNameText = findViewById(R.id.nameText)
        myAgeText = findViewById(R.id.ageText)
        myJobText = findViewById(R.id.jobText)





    }

    fun createUser (view: View) {
        val name: String = myNameText.text.toString()
        // val age: Int = myAgeText.text.toString().toIntOrNull() ?: 0 // daha kısa kullanım
        var age: Int = 0
        if (myAgeText.text.toString().toIntOrNull() == null) {
            Toast.makeText(this, "Please enter a valid age", Toast.LENGTH_LONG).show()

        } else {
            age= myAgeText.text.toString().toInt()
        }
        val job: String= myJobText.text.toString()
        val person = Person(name, age, job)
        myTextView.text = "Name: ${person.name}\nAge: ${person.age}\nJob: ${person.job}"




    }
}