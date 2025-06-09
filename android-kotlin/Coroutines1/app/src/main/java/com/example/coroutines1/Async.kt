package com.example.coroutines1

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){

    println("---ASYNC----")
    var name = ""
    var age = 0
   /* runBlocking {
        launch {
            name = fetchName()
        }
        launch {
            age = fetchAge()
        }
        launch {
            println("${name} : ${age}")
        }

    }*/

    runBlocking {
        val asyncName = async { name = fetchName() }
        val asyncAge = async { age = fetchAge() }

        asyncName.await()
        asyncAge.await()
        println("${name} : ${age}")

    }
}

suspend fun fetchName(): String {
    return "Arif"
}
suspend fun fetchAge(): Int {
    delay(1000)
    return 22
}