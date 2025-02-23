package com.example.basiccalculator

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.basiccalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private  var firstNumber: Double = 0.0
    private  var secondNumber: Double= 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)









    }
    fun isValidNumber(): Boolean {
        if (binding.number1Text.text.toString().toDoubleOrNull() == null || binding.number2Text.text.toString().toDoubleOrNull() == null) {
            binding.resultText.setText("Please enter a valid number")
            return false
        }
        else{
            firstNumber = binding.number1Text.text.toString().toDouble()
            secondNumber = binding.number2Text.text.toString().toDouble()
            return true

        }
    }
    fun onSumClick(view: View)  {
        if (isValidNumber()) {
            val result = firstNumber + secondNumber
            binding.resultText.text = "Result: $result"
            binding.number1Text.text.clear()
            binding.number2Text.text.clear()
        }



    }fun onSubstractClick(view: View)  {
        if(isValidNumber()){
            val result = firstNumber - secondNumber
            binding.resultText.text = "Result: $result"
            binding.number1Text.text.clear()
            binding.number2Text.text.clear()
        }


    }
    fun onMultiplyClick(view: View)  {
        if(isValidNumber()){
            val result = firstNumber * secondNumber
            binding.resultText.text = "Result: $result"
            binding.number1Text.text.clear()
            binding.number2Text.text.clear()
        }


    }
    fun onDivideClick(view: View)  {
        if (isValidNumber()){
            val result = firstNumber / secondNumber
            binding.resultText.text = "Result: $result"
            binding.number1Text.text.clear()
            binding.number2Text.text.clear()
        }


    }

}