package com.example.googlemaps.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.googlemaps.BitmapByteArrayConverter
import com.example.googlemaps.FavoritesActivity
import com.example.googlemaps.MapsActivity
import com.example.googlemaps.R
import com.example.googlemaps.database.Place
import com.example.googlemaps.database.PlaceDao
import com.example.googlemaps.database.PlaceDatabase
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlaceAdapter (private val context: Context, private val placeList : List<Place>)
    :RecyclerView.Adapter<PlaceAdapter.PlaceCardHolder>(){
        private val compositeDisposable = CompositeDisposable()
        private lateinit var placeDao : PlaceDao
        private lateinit var db : PlaceDatabase


    class PlaceCardHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val cardImage = itemView.findViewById<ImageView>(R.id.card_image)
        val placeName = itemView.findViewById<TextView>(R.id.card_placeTxt)
        val placeComment = itemView.findViewById<TextView>(R.id.card_commentTxt)
        val cardItem = itemView.findViewById<CardView>(R.id.cardItem)
        val deleteBtn = itemView.findViewById<Button>(R.id.card_deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceCardHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.place_card_layout,parent,false)
        return PlaceCardHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceCardHolder, position: Int) {
        holder.placeName.text = placeList[position].name.toString()
        holder.placeComment.text = placeList[position].comment.toString()

        val currentCard = holder.cardItem
        val currentByteArray = placeList[position].image
        val currentBitmap = BitmapByteArrayConverter.byteArrayToBitmap(currentByteArray)
        holder.cardImage.setImageBitmap(currentBitmap)

        //go to map
        currentCard.setOnClickListener { view ->
            val intent = Intent(context, MapsActivity :: class.java).apply {
                putExtra("lat",placeList[position].lat.toDouble())
                putExtra("lng",placeList[position].lng.toDouble())
                putExtra("cameFromFavorites",true)
            }
            Snackbar.make(view,"Show on Maps", Snackbar.LENGTH_INDEFINITE)
                .setAction ("Go To Map") {
                    context.startActivity(intent)
                }.show()
        }

        val cardDeleteBtn = holder.deleteBtn
        cardDeleteBtn.setOnClickListener { view ->
            db = Room.databaseBuilder(context, PlaceDatabase:: class.java,"PlaceDB")
                .build()
            placeDao = db.placeDao()

            placeDao.delete(placeList[position])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // uı güncellemesi için
                .subscribe({
                    Toast.makeText(context,"Deleted", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, FavoritesActivity:: class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    context.startActivity(intent)
                    }
                    ,{ error -> println("Silinirken Hata oluştu") })


        }


    }

    override fun getItemCount(): Int {
        return placeList.size
    }

}