package com.example.androiduicomponent

import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androiduicomponent.databinding.ActivityListAndGridViewBinding

class ListAndGridViewActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListAndGridViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListAndGridViewBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        val countryList = arrayListOf<String>(
            "Turkey",
            "Germany",
            "England",
            "ABD"
        )

        val listAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,countryList)

        binding.listView.adapter = listAdapter
        binding.gridView.adapter = listAdapter

        binding.listView.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(applicationContext,"Selected Item = ${countryList.get(position)}",Toast.LENGTH_SHORT)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.gridView.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(applicationContext,"Selected Item = ${countryList.get(position)}",Toast.LENGTH_SHORT)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }




    }
}