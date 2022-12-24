package com.example.crypto_news_app.services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.crypto_news_app.model.Coin

@Dao
interface CoinDao {

@Insert
suspend fun insertAll(vararg Coin: Coin):List<Long>

@Query("SELECT * FROM coin")
suspend fun getAllCoin():List<Coin>

@Query("SELECT * FROM coin  WHERE uuid=:CoinId")
suspend fun getCoin(CoinId:Int):Coin

@Query("DELETE FROM coin")
suspend fun deleteAll()

}