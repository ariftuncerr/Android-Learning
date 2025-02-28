package com.example.oopkotlinexamplescenerio

class Student (var studentNumber:String, userName:String, email:String,userType:String):User(userName,email,userType)  {
    fun showLessons(){
        println("Lessons")

    }
    

}