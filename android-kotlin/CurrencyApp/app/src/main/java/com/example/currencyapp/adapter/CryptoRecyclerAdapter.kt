package com.example.currencyapp.adapter

import android.graphics.Color
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.currencyapp.R
import com.example.currencyapp.model.CryptoModel

class CryptoRecyclerAdapter (private val cryptoList: ArrayList<CryptoModel>,private val listener: Listener): RecyclerView.Adapter<CryptoRecyclerAdapter.RowHolder>() {
    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }
    private val colors = arrayOf("#3F51B5","#2196F3","#03A9F4","#00BCD4","#009688","#4CAF50","#8BC34A","#CDDC39","#FFEB3B","#FFC107","#FF9800","#FF5722","#795548","#9E9E9E","#607D8B")

    class RowHolder (view: View) : RecyclerView.ViewHolder(view){
        fun bind(cryptoModel: CryptoModel, colors : Array<String>, position: Int, listener: Listener ){
            itemView.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }
            itemView.setBackgroundColor((Color.parseColor(colors[position % 15])))
            itemView.findViewById<TextView>(R.id.currencyText).text = cryptoModel.currency
            itemView.findViewById<TextView>(R.id.priceText).text = cryptoModel.price


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.row_recycler,parent,false)
        return RowHolder(view)

    }

    override fun getItemCount(): Int {
        return cryptoList.count()
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cryptoList[position],colors,position,listener)
    }


}