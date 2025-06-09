package com.example.retrofittest.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittest.R
import com.example.retrofittest.databinding.ItemLocationRowBinding
import com.example.retrofittest.model.LocationItem
import com.example.retrofittest.view.DetailActivity

class LocationAdapter(
    private val context: Context,
    private val locationList: List<LocationItem>
) : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    private val PREF_NAME = "favorites"
    private val FAVORITE_KEY = "favorite_ids"
    private val sharedPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    // Kalıcı favorileri belleğe çek
    private val favoriteSet: MutableSet<String> =
        sharedPrefs.getStringSet(FAVORITE_KEY, emptySet())?.toMutableSet() ?: mutableSetOf()

    inner class LocationViewHolder(val binding: ItemLocationRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = ItemLocationRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding)
    }

    override fun getItemCount(): Int = locationList.size

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val item = locationList[position]
        holder.binding.txtLocationName.text = item.name

        // Favori durumu kontrol ve ikon ata
        val isFavorite = favoriteSet.contains(item.id.toString())
        val iconRes = if (isFavorite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
        holder.binding.btnFavorite.setImageResource(iconRes)

        // Kalp ikonuna tıklama işlemi
        holder.binding.btnFavorite.setOnClickListener {
            
            toggleFavorite(item)
            notifyItemChanged(position)
        }

        // Konum satırına tıklama → Detay sayfasına geçiş
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("location_item", item)
            context.startActivity(intent)
        }
    }

    private fun toggleFavorite(item: LocationItem) {
        val idStr = item.id.toString()
        if (favoriteSet.contains(idStr)) {
            favoriteSet.remove(idStr)
        } else {
            favoriteSet.add(idStr)
        }
        // Kalıcı olarak güncelle
        sharedPrefs.edit().putStringSet(FAVORITE_KEY, favoriteSet).apply()
    }
}
