package com.example.crypto_news_app.VM

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crypto_news_app.model.Coin
import com.example.crypto_news_app.model.ApiData
import com.example.crypto_news_app.services.CoinAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

//lifecycle ve livedata kütüphenelerini bil
class CoinListVM:ViewModel() {
    val Coinler =MutableLiveData<List<Coin>>()
    private val disposable = CompositeDisposable()
//    val CoinHatamesaji =MutableLiveData<List<Coin>>()
//    val CoinYukleniyor =MutableLiveData<List<Coin>>()
    private val CoinApiServis=CoinAPIService()

    //ornek data
    fun refreshData(){
//        val btc = Coin("btc","17000")
//        val eth = Coin("eth","1300")
//        val audio = Coin("audio","0,14")
//        val avax = Coin("avax","13")
//
//        val coinlist= arrayListOf<Coin>(btc,eth,audio,avax)
//        Coinler.value=coinlist

        verileriInternettenAl()

    }

    private fun verileriInternettenAl() {
        System.out.println("adads")

      //System.out.println(CoinApiServis.getData())

        disposable.add(
            CoinApiServis.getData()
                .subscribeOn(Schedulers.newThread())//yeni threadde işlemler asenkron ve kullanıcı beklemesin programı diye
                .observeOn(AndroidSchedulers.mainThread())//mainde gözlemler
                .subscribeWith(object : DisposableSingleObserver<ApiData>(){
                    override fun onSuccess(t: ApiData) {
                        System.out.println("------77777777-----")
                        Coinler.value=t.toCoinList()
                        //basarılı
                   //     Toast.makeText(getApplication(),"Besinleri internet'ten Aldık" , Toast.LENGTH_LONG).show()
                    }
                    override fun onError(e: Throwable) {
                        System.out.println("------hata-----")
                        System.out.println( e.printStackTrace().toString())
                    }

                })
        )
    }


}