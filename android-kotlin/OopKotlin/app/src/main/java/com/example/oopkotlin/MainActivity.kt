package com.example.oopkotlin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var p1 : Person = Person("Arif", "Engineer", 2003)

       // println("Name : ${p1.name}") // değişken tanımlanırken private olarak tanımlandı erişim sağlanamaz get ve set tanımlanmadı
        println(p1.age.toString()) //değişken private tanımlanmadı erişim sağlandı
        p1


    }
}