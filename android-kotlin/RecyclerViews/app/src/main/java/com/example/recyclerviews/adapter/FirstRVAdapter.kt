package com.example.recyclerviews.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.ListView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviews.R
import com.google.android.material.snackbar.Snackbar

class FirstRVAdapter(
    private val context: Context,
    private val CityList: List<MutableList<String>>)
    : RecyclerView.Adapter<FirstRVAdapter.CardHolder>() {


    class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val fourCityList : GridView = itemView.findViewById<GridView>(R.id.listView)
        val card : CardView = itemView.findViewById<CardView>(R.id.cardView)
        val button : Button = itemView.findViewById<Button>(R.id.listBtn)

    }

    //XML recycler_row -> cardLayout bağlama işlemi
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card1_layout,parent,false)
        return CardHolder(view)
    }

    //her eleman görünmüne veri bağlanır
    override fun onBindViewHolder(
        holder: CardHolder,
        position: Int,
    ) {
        val currentCity = CityList[position]
        val listAdapter = ArrayAdapter(context,android.R.layout.simple_list_item_1,android.R.id.text1,currentCity)
        holder.fourCityList.adapter = listAdapter

        holder.button.setOnClickListener { view: View ->
            Snackbar.make(view,"$position tıklanıldi", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return CityList.size
    }





}