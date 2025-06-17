package com.example.guessthenumber

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.saytahmin.R
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var tvMessage: TextView
    private lateinit var etGuess: EditText
    private lateinit var btnGuess: Button
    private lateinit var btnRestart: Button

    private var randomNumber = 0
    private var triesLeft = 5

    private fun generateRandomNumber() {
        randomNumber = Random.nextInt(1, 101)
        triesLeft = 5
        etGuess.text.clear()
        tvMessage.text = "1 ile 100 arasında bir sayı tahmin et!"
        btnRestart.visibility = Button.GONE
        btnGuess.isEnabled = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMessage = findViewById(R.id.tvMessage)
        etGuess = findViewById(R.id.etGuess)
        btnGuess = findViewById(R.id.btnGuess)
        btnRestart = findViewById(R.id.btnRestart)

        generateRandomNumber()

        btnGuess.setOnClickListener {
            val guess = etGuess.text.toString().toIntOrNull()
            if (guess == null || guess !in 1..100) {
                tvMessage.text = "Lütfen 1 ile 100 arasında bir sayı girin."
                return@setOnClickListener
            }

            triesLeft--
            when {
                guess == randomNumber -> {
                    tvMessage.text = "Tebrikler! Doğru tahmin: $guess 🎉"
                    btnGuess.isEnabled = false
                    btnRestart.visibility = Button.VISIBLE
                }
                triesLeft == 0 -> {
                    tvMessage.text = "Tahmin hakkınız bitti! Sayı: $randomNumber"
                    btnGuess.isEnabled = false
                    btnRestart.visibility = Button.VISIBLE
                }
                guess < randomNumber -> {
                    tvMessage.text = "Daha büyük bir sayı dene. Kalan hakkın: $triesLeft"
                }
                else -> {
                    tvMessage.text = "Daha küçük bir sayı dene. Kalan hakkın: $triesLeft"
                }
            }
        }

        btnRestart.setOnClickListener {
            generateRandomNumber()
        }
    }
}
