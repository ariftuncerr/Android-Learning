package com.example.retrofittest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofittest.model.CityResponse
import com.example.retrofittest.repository.CityRepository
import kotlinx.coroutines.launch

class CityViewModel : ViewModel(){
    private val repository = CityRepository()

    //verilen tutulacağı mutableLiveData nesnesi
    val cityResponse = MutableLiveData<CityResponse?>()
    val isError = MutableLiveData<Boolean>()


    //verilerin sayfa sayfa yakalanacağı bölüm
    fun fetchCities(page : Int){
        viewModelScope.launch{
            try {
                val response = repository.getCities(page)
                if (response.isSuccessful && response.body() != null){
                    val data = response.body()
                    Log.e("API RESPONSE","Gelen Şehir Sayısı: ${data?.data?.size}")
                    cityResponse.postValue(data)
                    isError.postValue(false)
                }
                else{
                    Log.e("API_ERROR","Başarısız response: ${response.code()}")
                    isError.postValue(true)
                }
            }
            catch (_: Exception){
                Log.e("API_ERROR","Hata")
                isError.postValue(true)
            }


        }
    }
}