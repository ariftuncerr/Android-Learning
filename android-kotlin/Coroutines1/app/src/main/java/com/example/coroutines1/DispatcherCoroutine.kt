package com.example.coroutines1

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun dispatcher(){
    //dispatcher lar bir coroutine'nin hangi threadde çalışacağını belirler

    //Dispatchers.Main -> UI güncellemeleri için kullanılır

    runBlocking {
        CoroutineScope(Dispatchers.Main).launch {
            println("Dispatchers.Main->"+ Thread.currentThread().name)
        }
        CoroutineScope(Dispatchers.Default).launch {
            println("Dispatchers.Default->"+ Thread.currentThread().name)
        }
        CoroutineScope(Dispatchers.Unconfined).launch {
            println("Dispatchers.Unconfined->"+ Thread.currentThread().name)
        }
        CoroutineScope(Dispatchers.IO).launch {
            println("Dispatchers.IO->"+ Thread.currentThread().name)
        }

    }

}