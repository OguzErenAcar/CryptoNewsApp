package com.example.crypto_news_app.services

import com.example.crypto_news_app.model.Coin
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class CoinAPIService {

//BASE_URL="https://raw.githubusercontent.com/"
    private val BASE_URL=""
    private val api=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CoinApi::class.java)//burda belirtiyor

    fun getData():Single<List<Coin>>{
        return  api.getCoin()
    }

}