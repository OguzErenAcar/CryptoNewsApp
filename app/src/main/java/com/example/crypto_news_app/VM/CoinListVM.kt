package com.example.crypto_news_app.VM

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crypto_news_app.model.Coin
import com.example.crypto_news_app.model.ApiData
import com.example.crypto_news_app.services.CoinAPIService
import com.example.crypto_news_app.services.CoinDatabase
import com.example.crypto_news_app.util.OzelSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

//lifecycle ve livedata kütüphenelerini bil
class CoinListVM(application: Application):BaseVM(application){
    //view de buradan bir nesne oluşturup
    //observe live data ile bu listedeki datayı gözlemliyoruz
    val Coinler =MutableLiveData<List<Coin>>()
//    val CoinHatamesaji =MutableLiveData<List<Coin>>()
//    val CoinYukleniyor =MutableLiveData<List<Coin>>()

    private var guncellemeZamani=1.6*60*1000*1000*1000L

    private val disposable = CompositeDisposable()
    private val CoinApiServis=CoinAPIService()

    private val OzelSharedPreferences=OzelSharedPreferences(getApplication())
    //ornek data
    fun refreshData(){
//        val btc = Coin("btc","17000")
//        val eth = Coin("eth","1300")
//        val audio = Coin("audio","0,14")
//        val avax = Coin("avax","13")
//
//        val coinlist= arrayListOf<Coin>(btc,eth,audio,avax)
//        Coinler.value=coinlist

        val kaydedilmeZamani=OzelSharedPreferences.zamaniAL()
        System.out.println("fark"+(System.nanoTime()- kaydedilmeZamani!!))
        if(kaydedilmeZamani!=null &&kaydedilmeZamani!=0L &&System.nanoTime()-kaydedilmeZamani<guncellemeZamani){
            verileriSQLitetanAl()
        }else{
            verileriInternettenAl()

        }

    }
    private fun verileriSQLitetanAl() {

        launch {
            val coinlistesi= CoinDatabase(getApplication()).CoinDao().getAllCoin()
            CoinleriGoster(coinlistesi)
            Toast.makeText(getApplication(),"coinler sqltan alındı",Toast.LENGTH_LONG).show()
        }
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
                        //internetten çekilen verileri sqlite ta saklayıp
                       sqliteSakla(t.toCoinList())
                        //basarılı
                        Toast.makeText(getApplication(),"coinler interenetten alındı",Toast.LENGTH_LONG).show()
                    }
                    override fun onError(e: Throwable) {
                        System.out.println("------hata-----")
                        System.out.println( e.printStackTrace().toString())
                    }

                })
        )
    }

    //coinleri en son gösterdik
    private fun CoinleriGoster(CoinListesi: List<Coin>){
        Coinler.value=CoinListesi
    }


    private fun sqliteSakla(CoinListesi: List<Coin>){

        launch{
            //burdan yeni thread ile room oluşturup
            val dao = CoinDatabase(getApplication()).CoinDao()
            dao.deleteAll()
            val uuidList= dao.insertAll(*CoinListesi.toTypedArray())//listeyi tekil hale getiriyor for gibi

            var i =0
            while(i<CoinListesi.size){
                CoinListesi[i].uuid=uuidList[i].toInt()
                //gelen idleri modeldeki idlere eşitledik
                i+=1
            }

            CoinleriGoster(CoinListesi)
        }
        OzelSharedPreferences.zamaniKaydet(System.nanoTime())
    }

}