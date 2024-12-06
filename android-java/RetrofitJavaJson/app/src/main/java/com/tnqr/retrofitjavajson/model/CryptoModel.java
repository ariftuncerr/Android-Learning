package com.tnqr.retrofitjavajson.model;


import com.google.gson.annotations.SerializedName;


public class CryptoModel {

    @SerializedName("currency")
    private String currency;

    @SerializedName("price")
    public String price;

}
