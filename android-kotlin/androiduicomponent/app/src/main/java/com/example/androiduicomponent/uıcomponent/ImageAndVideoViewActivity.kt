package com.example.androiduicomponent.uıcomponent

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.example.androiduicomponent.R
import com.example.androiduicomponent.databinding.ActivityImageAndVideoViewBinding

class ImageAndVideoViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageAndVideoViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageAndVideoViewBinding.inflate(layoutInflater)

        val view : View = binding.root
        setContentView(view)

        //imageView
        binding.image1Btn.setOnClickListener { l : View ->
            binding.imageView.setImageResource(R.drawable.baseline_directions_car_24)
        }
        binding.image2Btn.setOnClickListener { l : View ->
            binding.imageView.setImageResource(R.drawable.car1)
        }

        //video view
        binding.videoBtn1.setOnClickListener { l: View ->
            val uri : Uri = Uri.parse("android.resource://$packageName/${R.raw.example_video}")
            binding.videoView.setVideoURI(uri)

            val mediaController = MediaController(this)
            mediaController.setAnchorView(binding.videoView)
            binding.videoView.setMediaController(mediaController)

            binding.videoView.start()
        }


    }
}