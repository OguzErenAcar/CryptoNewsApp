package com.example.crypto_news_app.VM

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crypto_news_app.model.Coin

//lifecycle ve livedata kütüphenelerini bil
class CoinListVM:ViewModel() {
    val Coinler =MutableLiveData<List<Coin>>()
//    val CoinHatamesaji =MutableLiveData<List<Coin>>()
//    val CoinYukleniyor =MutableLiveData<List<Coin>>()


    //ornek data
    fun refreshData(){
        val btc = Coin("btc","17000")
        val eth = Coin("eth","1300")
        val audio = Coin("audio","0,14")
        val avax = Coin("avax","13")

        val coinlist= arrayListOf<Coin>(btc,eth,audio,avax)
        Coinler.value=coinlist

    }


}