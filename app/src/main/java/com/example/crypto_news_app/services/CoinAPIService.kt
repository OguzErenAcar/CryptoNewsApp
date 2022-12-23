package com.example.crypto_news_app.services

import com.example.crypto_news_app.model.ApiData
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CoinAPIService {

//BASE_URL="https://raw.githubusercontent.com/"
    private val BASE_URL="https://coinranking1.p.rapidapi.com/"
    private val api=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CoinApi::class.java)//burda belirtiyor api bu sınıf türünden dönüyor

    //bunun coin listesi dönmesi gerek
   // List<Single<Coin>>
    fun getData():Single<ApiData>{
        return  api.getApiData()
    }

}