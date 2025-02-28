package com.example.oopkotlin

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.util.Date

class Person (name: String, job: String, birthYear: Int,private var salary :Double){
    private var name :String = name




    var age :Int = 2025 - birthYear


    var job :String = job
        private set
        get

    fun getSalary():Double{
        return salary
    }
    fun setSalary(salary: Double){
        if(salary >= this.salary){
            this.salary = salary
        }
        else{
            println("Yeni girilen maaş değeri eski maaş değerinden düşük olamaz")
        }

    }

}