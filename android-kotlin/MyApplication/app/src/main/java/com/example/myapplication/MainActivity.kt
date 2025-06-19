package com.example.myapplication

import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var point = 0
        var i =0
        binding.skorTxt.text = ""
         val bilmecelerList = listOf(
             "Kanadı var kuş değil suda yüzer gemi değil",
             "Soğuk adamın burnuyum toprağın altıdır yurdum"
        )
        val cevapList = listOf("Balık","Havuç")
        binding.sorutxt.text = bilmecelerList[i]

        binding.TahminYapBtn.setOnClickListener {
            val cevap = binding.cevapTxt.text.toString()

            if(cevap == cevapList[i]){
                Toast.makeText(applicationContext,"Doğru", Toast.LENGTH_SHORT).show()
                point++
                binding.skorTxt.text = "Skor: $point"

                binding.sorutxt.text = bilmecelerList[++i].toString()

            }
            else{
                Toast.makeText(applicationContext,"Yanlış Tekrar dene", Toast.LENGTH_SHORT).show()


            }
        }

    }



}