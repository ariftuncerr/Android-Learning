package com.example.lambdaexpressions

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
        //lambda expression
        // degisken gibi fonkisyon yazmamızı sağlarlar
        val testString = {myString : String -> println(myString) }
        testString("Hello World")

        val multiplyFunc = {a: Int , b : Int -> a*b}
        println(multiplyFunc(1,4))

        val DivideFunc : (Int, Int) -> Int = { a, b -> a/b}
        var result = DivideFunc(4,2)

        fun downloadCars (url : String , completion: (carName: Array<String>)->Unit){
            //bu fonksiyonda asenkron olarak arka planda araçlar inecek ve bu işlem bittiğinde
            //daha sonradan initilaze edilen completion çalışacak ve indirme işleminin bittiği belli olacak

            // dowload()
            //
            var cars = arrayOf("ford1","ford2","ford3")
            completion(cars) //CallBack fonkisyonu çağır

        }
        downloadCars("www.ford.com" ,{carNames-> //carNames = cars
            for (car in carNames){
                println(car)
            }

        })




    }
}