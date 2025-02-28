package com.example.oopkotlin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Objects

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

        var p1 : Person = Person("Arif", "Engineer", 2003,2000.0)


       // println("Name : ${p1.name}") // değişken tanımlanırken private olarak tanımlandı erişim sağlanamaz get ve set tanımlanmadı
        println(p1.age.toString()) //değişken private tanımlanmadı erişim sağlandı
        //p1.age = 30 // set metodu private ile atandı değiştirilemez
        println(p1.job)
        println(p1.age) // get metodu private olarak atanmadı erişim sağlanabilir


        //Car and factorty inheritance child class

        var f1 = Factory ("Factory of Reno","Bursa")
        var car1 = Car("Renault Clio ",2021 , "Factory of Renault","Bursa", 200000.0, "Red")
        var car2 = Car ("Renault Megane ",2022 , "Factory of Renault ", "İstanbul" )
        var car3  = Car ("Ford focus", 2021,"Factory of Ford", "Ankara")

        var cars  = mutableListOf<Car>()
        cars.add(car1)
        cars.add(car2)
        cars.add(car3)
        for (car in cars){
            println("FactoryName"+ car.factoryName)
            println("Brand"+ car.brandName)
            println("Year"+ car.year )

        }








    }
}