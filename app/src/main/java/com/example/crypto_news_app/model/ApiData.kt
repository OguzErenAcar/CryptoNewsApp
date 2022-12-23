package com.example.crypto_news_app.model

import androidx.annotation.AnyRes
import com.example.crypto_news_app.VM.CoinListVM
import com.example.crypto_news_app.model.Coin
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.LinkedTreeMap
import io.reactivex.Single
import org.json.JSONObject
import org.json.JSONTokener
import java.util.*
import kotlin.collections.ArrayList

data class ApiData (
    @SerializedName("data")//burası apideki ile aynı olmalı
    val data : LinkedTreeMap<String, Any>
    ){

  //  data?.getString("coins")?.length ?:nedir
    //json parse
    fun toCoinList():List<Coin>{

      val CoinList:List<Coin>
      CoinList= ArrayList<Coin>()

      val array :ArrayList<Any>
      array = data.get("coins") as ArrayList<Any>//bura gson ile direk alınablr ve döngüye gerek kalmayablr

      for (i in array){
        val Coin:LinkedTreeMap<String,Any>
        Coin = i as LinkedTreeMap<String,Any>

        val coinname:String= Coin.get("symbol") as String
        val coinprice:String= Coin.get("price") as String
        val iconUrl:String=Coin.get("iconUrl") as String
        val Coin_=Coin(coinname,coinprice,iconUrl)
        CoinList.add(Coin_)

      }
      return CoinList
  }

//        val CoinList:List<Coin>
//        CoinList= ArrayList<Coin>()
//
//       val Coinarray = JSONTokener(data.get("coins") as String?).nextValue() as JSONObject
//
//        for (i in 0 until Coinarray.length()) {
//            val Coin_=Coin(Coinarray.getString("symbol"),Coinarray.getString("price"))
//            CoinList.add(Coin_)
//        }
//        return Single.just(CoinList)
    }
