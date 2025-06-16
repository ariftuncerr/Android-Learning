package com.example.recyclerviews

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviews.adapter.MovieRVAdapter
import com.example.recyclerviews.databinding.ActivityRecycler2Binding
import com.example.recyclerviews.model.Movie

class Recycler2Activity : AppCompatActivity() {
    private lateinit var binding : ActivityRecycler2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRecycler2Binding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)

        val movie1 = Movie("Mortal Kombat",R.drawable.mortal_kombat)
        val movie2 = Movie("Oppenheimer",R.drawable.openhimmer)
        val movie3 = Movie("Lord Of The Rings",R.drawable.lord_of_ring)
        val movie4 = Movie("Avatar",R.drawable.avatar)
        val movie5 = Movie("Mortal Kombat",R.drawable.mortal_kombat)
        val movie6 = Movie("Oppenheimer",R.drawable.openhimmer)
        val movie7 = Movie("Lord Of The Rings",R.drawable.lord_of_ring)
        val movie8 = Movie("Avatar",R.drawable.avatar)
        val movie9 = Movie("Mortal Kombat",R.drawable.mortal_kombat)
        val movie10 = Movie("Oppenheimer",R.drawable.openhimmer)
        val movie11 = Movie("Lord Of The Rings",R.drawable.lord_of_ring)
        val movie12 = Movie("Avatar",R.drawable.avatar)


        val movieList = listOf<Movie>(movie1,movie2,movie3,movie4,movie5,movie6,movie7,movie8,movie9,movie10,movie11,movie12)

        val adapter = MovieRVAdapter(movieList,this)
        binding.moviesRecyclerView.layoutManager = GridLayoutManager(this,2)
        binding.moviesRecyclerView.adapter = adapter



    }
}