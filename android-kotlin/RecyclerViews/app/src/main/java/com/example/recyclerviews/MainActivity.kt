package com.example.recyclerviews

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recyclerviews.databinding.ActivityMainBinding
import com.example.recyclerviews.databinding.ActivityRecycler1Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)


    }
    fun firstRecyclerViewBtnClick(view : View){
        val intent = Intent(applicationContext, Recycler1Activity :: class.java)
        startActivity(intent)
    }
    fun MovieRecyclerViewBtnClick(view : View){
        val intent = Intent(applicationContext, Recycler2Activity :: class.java)
        startActivity(intent)
    }

}