package com.example.retrofittest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittest.R
import com.example.retrofittest.databinding.ItemCityRowBinding
import com.example.retrofittest.model.CityData

class CityAdapter(private var cityList: List<CityData>) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private val expandedCityRows = mutableSetOf<Int>()

    inner class CityViewHolder(val binding: ItemCityRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = ItemCityRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(binding)
    }

    override fun getItemCount(): Int = cityList.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val currentCity = cityList[position]
        val isExpanded = expandedCityRows.contains(position)

        holder.binding.cityNameText.text = currentCity.city
        holder.binding.btnExpand.setImageResource(
            if (isExpanded) R.drawable.narrow_24 else R.drawable.expand_24
        )

        holder.binding.btnExpand.setOnClickListener {
            if (isExpanded) {
                expandedCityRows.remove(position)
            } else {
                expandedCityRows.add(position)
            }
            notifyItemChanged(position)
        }

        if (isExpanded) {
            holder.binding.locationRecyclerView.visibility = View.VISIBLE
            holder.binding.locationRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
            val context = holder.itemView.context
            holder.binding.locationRecyclerView.adapter = LocationAdapter(context, currentCity.locations)
        } else {
            holder.binding.locationRecyclerView.visibility = View.INVISIBLE
        }


        holder.binding.locationRecyclerView.isNestedScrollingEnabled = false
    }

    fun updateList(newList: List<CityData>) {
        cityList = newList
        notifyDataSetChanged()
    }
}
