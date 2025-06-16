package com.example.md3

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.md3.databinding.ActivityMainBinding
import com.example.md3.databinding.ActivityMd3FirstBinding

class MD3FirstActivity : AppCompatActivity(),SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityMd3FirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMd3FirstBinding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)

        //fab floating button
        var cityList = mutableListOf<String>("Ankara","İstanbul")
        var cityAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,cityList)
        binding.listView.adapter = cityAdapter
        binding.floatingActionButton.setOnClickListener { view ->
            val alertView: View = layoutInflater.inflate(R.layout.alert_text, null)
            AlertDialog.Builder(this)
                .setTitle("Enter new City")
                .setView(alertView)
                .setPositiveButton("OK") { dialog, _ ->
                    var newCity = alertView.findViewById<EditText>(R.id.newCitytTxt).text.toString()
                    cityList.add(newCity)
                    cityAdapter.notifyDataSetChanged()

                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()

        }

        //toolbar
        binding.materialToolbar.setLogo(R.drawable.baseline_directions_car_24)
        binding.materialToolbar.setTitle("MD3")
        binding.materialToolbar.setSubtitle("Material Design 3")
        setSupportActionBar(binding.materialToolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)

        val searchItem : MenuItem? = menu?.findItem(R.id.search_action)
        val searchView : SearchView? = searchItem?.actionView as? SearchView
        searchView?.setOnQueryTextListener(this)


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val result = when(item.itemId){
            R.id.action_info -> "Action Info"
            R.id.action_logout -> "Action Logout"
            R.id.action_profile -> "Action Profile"
            R.id.action_settings -> "Action Settings"
            else ->  null

        }
        Toast.makeText(applicationContext,result, Toast.LENGTH_SHORT).show()
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.e("Arama Sonucu","Gönderilen arama sonucu yer alır")
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e("Arama Sonucu","Harf Girdikçe sonuç gelir")
        return true
    }


}