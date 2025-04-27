package com.example.retrofittest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittest.R
import com.example.retrofittest.model.CityData
import com.example.retrofittest.model.CityResponse
import org.w3c.dom.Text

class CityAdapter(private val cityData: List<CityData>):
    RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
    class CityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cityText : TextView = itemView.findViewById(R.id.cityNameText)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city_row,parent,false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cityData.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.cityText.text = cityData[position].toString()
    }
}