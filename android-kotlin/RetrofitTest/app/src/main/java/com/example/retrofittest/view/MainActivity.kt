package com.example.retrofittest.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittest.adapter.CityAdapter
import com.example.retrofittest.databinding.ActivityMainBinding
import com.example.retrofittest.model.CityData
import com.example.retrofittest.model.CityResponse
import com.example.retrofittest.viewmodel.CityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cityAdapter: CityAdapter
    private val cityViewModel: CityViewModel by viewModels()

    private var isLoading = false
    private var currentPage = 1
    private var totalPages = 1

    private val allCities = mutableListOf<CityData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()

        handleFirstPage()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewCities.layoutManager = LinearLayoutManager(this)
        cityAdapter = CityAdapter(emptyList())
        binding.recyclerViewCities.adapter = cityAdapter

        binding.recyclerViewCities.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (!isLoading && lastVisibleItem >= totalItemCount - 1 && currentPage < totalPages) {
                    isLoading = true
                    binding.progressBarLoading.visibility = View.VISIBLE // Loading göster
                    currentPage++
                    cityViewModel.fetchCities(currentPage)
                }

            }
        })
    }

    private fun observeViewModel() {
        cityViewModel.cityResponse.observe(this) { response ->
            if (response != null) {
                val newData = response.data
                allCities.addAll(newData)
                cityAdapter.updateList(allCities)
                isLoading = false
                binding.progressBarLoading.visibility = View.GONE
            }
        }
    }



    private fun handleFirstPage() {
        val firstPageData = intent.getSerializableExtra("city_response") as? CityResponse
        val cityList = firstPageData?.data ?: emptyList()

        totalPages = firstPageData?.totalPage ?: 1

        allCities.clear()
        allCities.addAll(cityList)
        cityAdapter.updateList(allCities)
    }
   fun onFavoritesClick(view : View){

        val allLocations = allCities.flatMap { it.locations }
        val intent = Intent(this, FavoritesActivity::class.java)
        intent.putExtra("all_locations", ArrayList(allLocations))
        startActivity(intent)
    }
}
