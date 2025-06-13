package com.example.coroutines1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        /*println("-----COROUTINE------")
        coroutine()

        println("-----COROUTINE SCOPE------")
        coroutineScope()

        println("-----DISPATCHER------")
        dispatcher()

        println("-----SUSPEND FUNCTION-----")
        suspendFunction()*/


        println("-------Coroutine Exception Handling--------")

        //yöntem 1 önerilmez
        lifecycleScope.launch {
            try {
                throw Exception ("normal exception")
            }
            catch (e: Exception){
                println("Normal Exception Handling:"+e.printStackTrace())
            }
        }
        //yöntem 2

        val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("Coroutine Exception Handler:"+throwable.printStackTrace())
        }

        lifecycleScope.launch (handler){
            throw Exception ("coroutine error")

        }

        //eğer birden fazla launch olursa birindeki hata tüm scope' u iptal edebilir
        lifecycleScope.launch (handler) {
            launch {
                throw Exception("First launch exception")
            }
            launch {
                println("Second launch running")
            }

        }
        //önelemek için -> supervisor scope
        lifecycleScope.launch (handler) {
            supervisorScope {
                launch {
                    throw Exception("First launch exception")
                }
                launch {
                    println("Second launch running")
                }
            }

        }



    }
}