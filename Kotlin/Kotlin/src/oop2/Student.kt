package oop2

import java.util.*

class Student (val name: String, val age : Int, var email : String?, var number: Int ) {

    init {
        if (name.get(0) in 'a'..'z') println("Your name must be start with UpperCase")
        println("Name: " + name + "Age:" + age)

    }
}

