package com.example.coroutines1

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun coroutineScope(){


    //coroutine context gerekir bu da dispacther ile yönetilir
    var i = 1
    val coroutineScope =
        CoroutineScope(Dispatchers.Default).launch {

        repeat (20){
            delay(1000)
            println("coroutine times"+ i++)
        }
    }
    runBlocking {
        launch {
            delay(3000)
            coroutineScope.cancel()

        }
    }






}