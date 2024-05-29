package com.rahulyadav.imageloader.network

import com.rahulyadav.imageloader.model
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiInterface {


    @GET
    fun getTopHuntHeadLines(@Url url:String) : Call<List<model>>
}