package com.example.carsdetailsrecyclerview1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carsdetailsrecyclerview1.databinding.ActivityCarsDetailBinding
import com.example.carsdetailsrecyclerview1.databinding.RecyclerRowBinding

class CarAdapter (val carList : ArrayList<Cars>): RecyclerView.Adapter<CarAdapter.CarHolder>() {

    class CarHolder(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root){

    }

    //yukarıda oluşturduğum CarHolder ilk oluşturulduğunda ne olacak
    // viewbinding ile recyclerRow bağlanır
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarHolder {
        //buradaki RecyclerRowBinding recycler_row xml layout dosyasını buluyor ve bağlama işlemini yapıyor
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CarHolder(binding)
    }

    //kaç tane oluşturulacak. array list alınıp kullanılabilir
    override fun getItemCount(): Int {
        return carList.size
    }

    //bağlandıktan sonra ne olacak hangi texte hangi veri tutulacak
    override fun onBindViewHolder(holder: CarHolder, position: Int) {
        holder.binding.recyclerRowText.text = carList.get(position).name

        holder.itemView.setOnClickListener{
            mySingleton.chosenCar = carList.get(position)
            val intent  = Intent(holder.itemView.context,CarsDetailActivity::class.java)
           // intent.putExtra("car",carList.get(position))
            holder.itemView.context.startActivity(intent)
        }
    }
}