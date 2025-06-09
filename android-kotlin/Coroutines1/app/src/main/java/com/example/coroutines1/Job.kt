package com.example.coroutines1

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main (){
    println("----Job----")

    runBlocking {
        val job1 = launch {
            delay(2000)
            println("job 1 run")
            val job2 = launch {
                println("job 2 run")
            }
        }
        val job3 = launch {
            println("job 3 run")
        }
        job3.invokeOnCompletion {
            println("job3 bittikten sonra yapılacak işlemler")
        }
        job1.cancel()
    }
}