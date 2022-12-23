package com.example.crypto_news_app.util

import android.content.Context
import android.widget.ImageView
import androidx.constraintlayout.helper.widget.MotionPlaceholder
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.crypto_news_app.R

fun ImageView.gorselIndir(url:String?,placeholder:CircularProgressDrawable){
    val options= RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)
    Glide.with(context).setDefaultRequestOptions(options).load(url).override(60, 60).into(this)
}


fun placeholderYap(context:Context):CircularProgressDrawable{

    return  CircularProgressDrawable(context).apply {

     strokeWidth=8f
     centerRadius=40f
     start()
    }
}