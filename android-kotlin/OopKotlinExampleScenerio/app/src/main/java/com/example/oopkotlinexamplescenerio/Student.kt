package com.example.oopkotlinexamplescenerio

class Student (var studentNumber:String, userName:String, email:String,userType:String):User(userName,email,userType)  {
    fun showLessons(){
        println("Lessons")

    }


    override fun login(): String {
        return "Student Successfully logged"

    }

    override fun showInfos(): String {
        return "Student Number ${studentNumber} UserName: ${super.userName} Email: ${super.email}"
    }
    fun studentSpecialAttribute(): String{
        return "I have grades"
    }


}