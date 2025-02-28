package com.example.oopkotlinexamplescenerio

abstract class User ( userName: String,  email :String, userType: String) {

    protected var userName :String = userName

    protected var email : String = email

    protected var userType : String = userType

    abstract fun login () :String
    abstract fun showInfos(): String




}