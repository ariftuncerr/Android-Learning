package com.example.androidpermissions

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ImageAdapter (private val context: Context, private val imageList : List<Bitmap>): RecyclerView.Adapter<ImageAdapter.ImageCardHolder>(){
    class ImageCardHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val card : CardView = itemView.findViewById<CardView>(R.id.cardView)
        val cardImage : ImageView = itemView.findViewById<ImageView>(R.id.cardImageView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageCardHolder {
        val cardLayout = LayoutInflater.from(parent.context).inflate(R.layout.images_card_layout,parent,false)
        return ImageCardHolder(cardLayout)
    }

    override fun onBindViewHolder(holder: ImageCardHolder, position: Int) {
       holder.cardImage.setImageBitmap(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}