package com.example.crypto_news_app.VM

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crypto_news_app.model.Coin
import com.example.crypto_news_app.services.CoinDatabase
import kotlinx.coroutines.launch

class Coin_infoVM(application: Application): BaseVM(application){

    val CoinLiveData=MutableLiveData<Coin>()

    fun roomVerisiniAl(uuid:Int){

        launch {

            val dao  =CoinDatabase(getApplication()).CoinDao()
            val coin =dao.getCoin(uuid)
            CoinLiveData.value=coin
        }
    }

}