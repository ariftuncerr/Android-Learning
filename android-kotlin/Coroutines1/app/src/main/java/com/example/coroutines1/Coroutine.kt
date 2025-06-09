package com.example.coroutines1

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun coroutine(){
    // arka planda ön yüzden bir şey beklemeden buradaki işlemler gerçekleşir
    /*  GlobalScope.launch {
          repeat (1000){
              launch {
                  println("Global Scope")
              }
          }
      }*/

    //runblocking
    println("Run blocking start")
    // çalışmayı bloklayarak bir sonraki işleme geçmeye izin vermez.
    runBlocking {
        launch {
            delay(5000)
            println("run blocking ")
        }
    }

    println("run blocking end")





    //global scope başladıktan sonra içerisindeki işlemler için beklemez sonraki işlemlere geçilir
    // bekleme geçtikten sonra içerisindeki işlemler devam eder.
    println("global scope start")
    GlobalScope.launch {
        delay(5000)
        println("global scope waiting")
    }
    println("global scope end")
}