package com.example.catchspiderman

import android.os.Bundle
import android.os.Looper
import android.view.SoundEffectConstants
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.catchspiderman.databinding.ActivityMainBinding
import java.util.logging.Handler
import kotlin.properties.Delegates
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  var gameTime : Int =0
    var runnable : Runnable = Runnable {}
    var handler : android.os.Handler = android.os.Handler(Looper.getMainLooper())
    var images :List <ImageView> by Delegates.notNull()
    var score : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        restartImages()






    }
    fun restartImages(){
        images = listOf(
            binding.imageView1,
            binding.imageView2,
            binding.imageView3,
            binding.imageView4,
            binding.imageView5,
            binding.imageView6,
            binding.imageView7,
            binding.imageView8,
            binding.imageView9,
            binding.imageView10,
            binding.imageView11,
            binding.imageView12,
            binding.imageView13,
            binding.imageView14,
            binding.imageView15,
            binding.imageView16,
            binding.imageView17,
            binding.imageView18,
            binding.imageView19,
            binding.imageView20,
            binding.imageView21,
            binding.imageView22,
            binding.imageView23,
            binding.imageView24,
            binding.imageView25,
            binding.imageView26,
            binding.imageView27,
            binding.imageView28,
            binding.imageView29,
            binding.imageView30,
            binding.imageView31,
            binding.imageView32,
            binding.imageView33,
            binding.imageView34,
            binding.imageView35,
            binding.imageView36,
            binding.imageView37,
            binding.imageView38,
            binding.imageView39,
            binding.imageView40,
            binding.imageView41,
            binding.imageView42,
            binding.imageView43,
            binding.imageView44,
            binding.imageView45,

        )
        for(i in images){
            i.visibility = View.INVISIBLE
            i.isClickable = false
        }
    }
    fun onStartClick(view: View) {
        val input = EditText(this)
        var alert = AlertDialog.Builder(this)

        alert.setTitle("Spiderman")
        alert.setMessage("Enter the game time you want to play")
        alert.setView(input)

        alert.setPositiveButton("Start") { dialog, which ->
            if(input.text.toString().toIntOrNull() == null){
                Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
            }
            else{
                gameTime = input.text.toString().toInt()
                binding.startButton.isEnabled = false
                startRunnable()
                score =0


            }


        }
        alert.setNegativeButton("Cancel"){dialog,which->
            dialog.cancel()
        }
        alert.show()

    }
    fun startRunnable(){
        var randomNumber =0


        runnable = object : Runnable {
            override fun run() {

                restartImages()
                if(gameTime == 0){
                    handler.removeCallbacks(runnable)
                    Toast.makeText(this@MainActivity, "Time over Check Your Score", Toast.LENGTH_SHORT).show()
                    binding.startButton.isEnabled = true


                }
                else{
                    randomNumber = Random.nextInt(1,46)
                    val visibleImage = images[randomNumber]
                    visibleImage.visibility = View.VISIBLE
                    visibleImage.isClickable = true
                    visibleImage.setOnClickListener({
                        visibleImage.visibility = View.INVISIBLE
                        score++
                        binding.scoreText.text = "Score: $score"
                        it.playSoundEffect(SoundEffectConstants.CLICK)

                    })
                    images[randomNumber].isClickable = true
                    gameTime--
                    binding.timeText.text = "Time: $gameTime"
                    handler.postDelayed(this, 600)
                }

            }
        }
        handler.post(runnable)

    }
    fun imageOnClikcked(view: View){


    }
}