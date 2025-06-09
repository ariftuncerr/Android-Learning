package com.example.retrofittest.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.retrofittest.databinding.ActivityDetailBinding
import com.example.retrofittest.model.LocationItem

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var location: LocationItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        location = intent.getSerializableExtra("location_item") as? LocationItem

        location?.let {
            binding.txtLocationName.text = it.name
            binding.txtDescription.text = it.description

            // Glide ile görsel yükle
            Glide.with(this)
                .load(it.image)
                .into(binding.imgLocation)

        }


    }

    // haritaya git
    fun onMapClick(view: View){
        location?.let {
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra("location_item", it)
            startActivity(intent)
        }

    }
}
