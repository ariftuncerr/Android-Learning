package com.example.androiduicomponent

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.MediaController
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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