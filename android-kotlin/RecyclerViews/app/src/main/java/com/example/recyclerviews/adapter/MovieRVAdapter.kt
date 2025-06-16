package com.example.recyclerviews.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviews.R
import com.example.recyclerviews.model.Movie

class MovieRVAdapter (
    private val movieList: List<Movie>,
    private val context: Context) : RecyclerView.Adapter<MovieRVAdapter.MovieCardHolder> (){

    class MovieCardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImage : ImageView = itemView.findViewById(R.id.movieImage)
        var movieName : TextView = itemView.findViewById(R.id.movieNameTxt)
        val addListBtn : Button = itemView.findViewById(R.id.addListBtn)


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieCardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card2_layout,parent,false)
        return MovieCardHolder(view)
    }

    override fun onBindViewHolder(
        holder: MovieCardHolder,
        position: Int
    ) {
        holder.movieImage.setImageResource(movieList[position].image)
        holder.movieName.text = movieList[position].name.toString()

    }

    override fun getItemCount(): Int {
        return movieList.size
    }



}