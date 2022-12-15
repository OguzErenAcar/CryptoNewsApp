package com.example.crypto_news_app.model

import com.google.gson.annotations.SerializedName

data class Coin (
    @SerializedName("coinName")
    val coinName: String?,
    @SerializedName("price")
    val price:String?,

    ) {
}