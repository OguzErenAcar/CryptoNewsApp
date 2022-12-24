package com.example.crypto_news_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
//sqlite da tablolamya yarar
@Entity
data class Coin (
    @ColumnInfo(name="coinName")//sqlite da bir kolon
    @SerializedName("coinName")
    val coinName: String?,
    @ColumnInfo(name="price")
    @SerializedName("price")
    val price:String?,
    @ColumnInfo(name="iconUrl")
    @SerializedName("iconUrl")
    val iconUrl:String?


    ) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}