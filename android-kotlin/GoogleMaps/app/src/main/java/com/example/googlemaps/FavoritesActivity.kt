package com.example.googlemaps

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.googlemaps.adapter.PlaceAdapter
import com.example.googlemaps.database.Place
import com.example.googlemaps.database.PlaceDao
import com.example.googlemaps.database.PlaceDatabase
import com.example.googlemaps.databinding.ActivityFavoritesBinding
import com.example.googlemaps.databinding.ActivityMainBinding
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavoritesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoritesBinding

    private lateinit var db : PlaceDatabase
    private lateinit var placeDao : PlaceDao
    private val compositeDisposable = CompositeDisposable() // kullan at
    private lateinit var placeList : MutableList<Place>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        db = Room.databaseBuilder(applicationContext, PlaceDatabase :: class.java,"PlaceDB")
            .build()
        placeDao = db.placeDao()

        getPlaces()


    }
    fun getPlaces(){
        compositeDisposable.add(
            placeDao.getAll()
                .subscribeOn(Schedulers.io()) // veriyi arka planda çek
                .observeOn(AndroidSchedulers.mainThread()) //uı güncellemelerini önde yap
                .subscribe({ list ->
                    list?.let {
                        placeList = it as MutableList<Place>
                        println("Liste alındı")
                        val placeAdapter = PlaceAdapter(this,placeList)
                        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
                        binding.recyclerView.adapter = placeAdapter
                    }
                },
                {error ->
                    println(error.localizedMessage)
                })
        )
    }


}