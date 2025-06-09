package com.example.coroutines1

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun suspendFunction(){
    runBlocking {
        launch {
            println("başladı")
            fetchData()
            println("bitti")
        }
    }

}

suspend fun fetchData(){
    delay(2000)
    println("veri geldi")

}