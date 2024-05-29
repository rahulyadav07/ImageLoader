package com.rahulyadav.imageloader.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {

    fun provideNetWorkService() : ApiInterface {
        return Retrofit.Builder()
            .baseUrl("https://www.upload-apk.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

}

//https://www.upload-apk.com/en/Zytp1r01gTz6drY

