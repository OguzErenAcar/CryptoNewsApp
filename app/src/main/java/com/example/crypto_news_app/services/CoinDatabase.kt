package com.example.crypto_news_app.services

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crypto_news_app.model.Coin
//room
@Database(entities=arrayOf(Coin::class),version=1)
    abstract class CoinDatabase : RoomDatabase(){

        abstract  fun CoinDao():CoinDao

        //Singleton
        companion object{

      @Volatile  private var instance:CoinDatabase? =null

      private val lock =Any()
            //daha önce oluşturulmuşsa onu kulan yoksa yeni olustur
      operator fun invoke(context:Context)= instance ?: synchronized(lock){

          instance ?: databaseOlustur(context).also{
              instance=it
          }
      }

        private fun databaseOlustur(context:Context)= Room.databaseBuilder(
            context.applicationContext,CoinDatabase::class.java,"coindatabase").build()
        }
    }