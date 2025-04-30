package com.example.retrofittest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofittest.model.CityResponse
import com.example.retrofittest.repository.CityRepository
import kotlinx.coroutines.launch

class CityViewModel : ViewModel() {
    private val repository = CityRepository()

    val cityResponse = MutableLiveData<CityResponse?>()
    val isError = MutableLiveData<Boolean>()

    fun fetchCities(page: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getCities(page)
                if (response.isSuccessful && response.body() != null) {
                    val newData = response.body()
                    Log.e("API RESPONSE", "Sayfa $page - Gelen Şehir Sayısı: ${newData?.data?.size}")

                    val currentData = cityResponse.value?.data ?: emptyList()
                    val updatedData = newData?.data ?: emptyList()
                    val combinedList = currentData + updatedData

                    val mergedResponse = newData?.copy(data = combinedList)
                    cityResponse.postValue(mergedResponse)

                    isError.postValue(false)
                } else {
                    Log.e("API_ERROR", "Başarısız response: ${response.code()}")
                    isError.postValue(true)
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "Hata: ${e.message}")
                isError.postValue(true)
            }
        }
    }
}
