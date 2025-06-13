package com.example.androiduicomponent

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androiduicomponent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)


        //toggle
        binding.switch1.setOnCheckedChangeListener{ buttonView, isChecked->
            if (isChecked) {
                Log.e("Switch Case","Switch is on")
            }
            else{
                Log.e("Switch Case","Switch is off")
            }
        }

        //switch
        binding.toggleButton.setOnCheckedChangeListener { buttonView ,isChecked ->
            if (isChecked) {
                Log.e("Toggle Case","Switch is on")
            }
            else{
                Log.e("Toggle Case","Switch is off")
            }

        }


        //checkbox
        binding.JavaCheckBox.setOnCheckedChangeListener { _, isSelected ->
            if (isSelected)
                Toast.makeText(this,"Java Selected", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this,"Java not Selected", Toast.LENGTH_SHORT).show()

        }

        binding.KotlinCheckBox.setOnCheckedChangeListener {_,isSelected ->
            if (isSelected)
                Toast.makeText(this,"Kotlin Selected", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this,"Kotlin not Selected", Toast.LENGTH_SHORT).show()

        }

        //RadioButton

        binding.tsRadioButton.setOnCheckedChangeListener { _,isSelected ->
            if (isSelected)
                 Toast.makeText(this,"Ts radio Button Selected", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this,"Ts is not selected", Toast.LENGTH_SHORT).show()

        }
        binding.GsRadioButton.setOnCheckedChangeListener { _,isSelected ->
            if (isSelected)
                Toast.makeText(this,"GS radio Button Selected", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this,"GS is not selected", Toast.LENGTH_SHORT).show()

        }



        //progressBar
        binding.progressBarSwitch.setOnCheckedChangeListener { _,isChecked ->
            if (isChecked){
                binding.progressBar.visibility = View.VISIBLE

            }
            else{
                binding.progressBar.visibility = View.INVISIBLE
            }
        }

        //seekBar
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                binding.seekkValueTxt.text = "Seek Point: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //kullaıcı dokunmaya başladığında
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //kullanıcı bıraktığında
            }

        })


        //rating Bar

        binding.ratingBar.setOnRatingBarChangeListener{ratingBar,rating,fromUser->
            binding.ratingValueTxt.text = "Rating Point ${rating*20}"
        }








    }
}
