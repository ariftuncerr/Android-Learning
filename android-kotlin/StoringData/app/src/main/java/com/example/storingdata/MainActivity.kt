package com.example.storingdata

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.storingdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var age : String =""
    private lateinit var sharedPrefs : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        sharedPrefs = this.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        age = sharedPrefs.getString("age", "0").toString()
        binding.ageText.setText("Your age: $age")


    }

    fun onSaveClick(view: View) {
        age = binding.ageEditText.text.toString()
        sharedPrefs.edit().putString("age", age).commit()
        binding.ageText.setText("Your age: $age")
        Toast.makeText(this,"Data saved", Toast.LENGTH_SHORT).show()


    }
    fun onDeleteClick(view: View) {
        sharedPrefs.edit().remove("age").commit()
        binding.ageText.setText("Your age: 0")
        Toast.makeText(this, "Data deleted", Toast.LENGTH_SHORT).show()
    }
}