package com.tnqr.retrofitjavajson.service;

import com.tnqr.retrofitjavajson.model.CryptoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {
    //GET //POST //UPDATE //DELETE
    //base url = www.website.com
    //

    @GET("atilsamancioglu/K21-JSONDataSet/blob/master/crypto.json")
     Call<List<CryptoModel>> getData();

    //


}
