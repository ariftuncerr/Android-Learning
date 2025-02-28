package com.example.oopkotlinexamplescenerio

class Teacher (branch: String ,userName: String,  email :String, userType: String): User (userName,email,userType) {
    var branch : String = branch

    override fun login(): String {
        return "Teacher successfully logged"
    }

    override fun showInfos(): String {
        return "UserName: ${super.userName} Branch: ${branch} Email: ${super.email}"

    }


}