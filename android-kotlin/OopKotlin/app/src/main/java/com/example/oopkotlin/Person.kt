package com.example.oopkotlin

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.util.Date

open class Person (name: String, job: String, birthYear: Int,private var salary :Double){
    var name :String = name
    var age :Int = 2025 - birthYear
        private set // dışarıdan erişim sağlanamaz

    var job :String = job
        private set
        get

    fun setJob(job: String){
        this.job = job
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