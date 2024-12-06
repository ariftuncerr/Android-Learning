package com.tnqr.retrofitjavajson.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tnqr.retrofitjavajson.R;
import com.tnqr.retrofitjavajson.model.CryptoModel;
import com.tnqr.retrofitjavajson.service.CryptoAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<CryptoModel> cryptoModels = new ArrayList<>();
    Retrofit retrofit;
    private String baseURL ="https://github.com/";
     //https://github.com/atilsamancioglu/K21-JSONDataSet/blob/master/crypto.json
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new GsonBuilder().setLenient().create();

        //RESTful API'larla iletişim kurmayı kolaylaştıran bir HTTP istemci kütüphanesi
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loadData();



    }
    private void loadData() {
        CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);

        Call<List<CryptoModel>> call = cryptoAPI.getData();
        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CryptoModel> responseList = response.body();
                    cryptoModels = new ArrayList<>(responseList);

                    // Test: Log CryptoModel data
                    for (CryptoModel crypto : cryptoModels) {
                        Log.d("CryptoData", "Currency: "+ ", Price: " + crypto.price);
                    }
                } else {
                    Log.e("ResponseError", "Response body is null or response is not successful");
                }
            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                Log.e("NetworkError", "Error fetching data: " + t.getMessage());
            }
        });
    }

}