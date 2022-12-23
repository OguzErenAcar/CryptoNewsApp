package com.example.crypto_news_app.services

import com.example.crypto_news_app.model.ApiData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers


//retrofit
//rx java obervable type -->SEARCH
interface CoinApi {

    @Headers(
        "X-RapidAPI-Key: 8370d9e021msh7f958074248e43cp1de392jsn1f21f57cf161",
        "X-RapidAPI-Host: coinranking1.p.rapidapi.com"
    )
    @GET("coins?")
    fun getApiData(): Single<ApiData>
}