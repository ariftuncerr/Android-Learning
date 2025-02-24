package com.example.intentapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.intentapp.databinding.ActivityMainBinding
import org.jetbrains.annotations.ApiStatus.Internal
import java.io.FileInputStream

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        println("hello")
        setContentView(view)
        enableEdgeToEdge()
        binding.nameText.setText("")

    }
    override fun onResume() {
        super.onResume()
        binding.nameText.setText("")
        println("onResume")
    }

    override fun onStart() {
        super.onStart()
        println("onStart")
        binding.nameText.setText("")

    }

    override fun onPause() {
        super.onPause()
        println("onPause")
    }
    override fun onStop() {
        super.onStop()
        println("onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }
    override fun onRestart() {
        super.onRestart()
        println("onRestart")
    }

    fun onNextPageClick (view: View){
        var alert = AlertDialog.Builder(this)
        alert.setTitle("GO TO NEXT PAGE")
        alert.setMessage("Are you sure that you want to go to the next page?")

        alert.setPositiveButton("Yes"){dialog, which ->
            val intent = Intent(this, SecondActivity::class.java)


            intent.putExtra("name",binding.nameText.text.toString())
            startActivity(intent)
        }
        alert.setNegativeButton("No"){dialog, which ->
            dialog.cancel()
        }
        alert.show()




    }

}