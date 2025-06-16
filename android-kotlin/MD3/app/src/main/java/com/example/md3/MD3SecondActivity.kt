package com.example.md3

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.md3.databinding.ActivityMd3FirstBinding
import com.example.md3.databinding.ActivityMd3SecondBinding

class MD3SecondActivity : AppCompatActivity(), SearchView.OnQueryTextListener{
    private lateinit var binding : ActivityMd3SecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMd3SecondBinding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)
        setSupportActionBar(binding.materialToolbar2)




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu2,menu)
        val searchItem = menu?.findItem(R.id.app_bar_search)
        val searchView = searchItem?.actionView as? SearchView
        searchView?.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
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