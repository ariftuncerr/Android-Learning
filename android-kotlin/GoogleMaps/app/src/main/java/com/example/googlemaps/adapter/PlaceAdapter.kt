package com.example.googlemaps.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.googlemaps.BitmapByteArrayConverter
import com.example.googlemaps.R
import com.example.googlemaps.database.Place

class PlaceAdapter (private val context: Context, private val placeList : List<Place>)
    :RecyclerView.Adapter<PlaceAdapter.PlaceCardHolder>(){


    class PlaceCardHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val cardImage = itemView.findViewById<ImageView>(R.id.card_image)
        val placeName = itemView.findViewById<TextView>(R.id.card_placeTxt)
        val placeComment = itemView.findViewById<TextView>(R.id.card_commentTxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceCardHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.place_card_layout,parent,false)
        return PlaceCardHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceCardHolder, position: Int) {
        holder.placeName.text = placeList[position].name.toString()
        holder.placeComment.text = placeList[position].comment.toString()

        val currentByteArray = placeList[position].image
        val currentBitmap = BitmapByteArrayConverter.byteArrayToBitmap(currentByteArray)
        holder.cardImage.setImageBitmap(currentBitmap)
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

}