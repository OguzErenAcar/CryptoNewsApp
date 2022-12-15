package com.example.crypto_news_app.services

import com.example.crypto_news_app.model.Coin
import io.reactivex.Single
import retrofit2.http.GET


//retrofit
//rx java obervable type -->SEARCH
interface CoinApi {

    //@GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    @GET()
    fun getCoin(): Single<List<Coin>>

}