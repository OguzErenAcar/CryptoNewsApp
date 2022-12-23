package com.example.crypto_news_app.VM

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crypto_news_app.model.Coin

class Coin_infoVM: ViewModel() {

    val CoinLiveData=MutableLiveData<Coin>()

    fun roomVerisiniAl(){
        val btc = Coin("btc","17000","asd")
        CoinLiveData.value=btc



    }

}