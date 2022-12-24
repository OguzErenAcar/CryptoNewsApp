package com.example.crypto_news_app.VM

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseVM(application: Application, ):AndroidViewModel(application),CoroutineScope {


    private val job= Job()
    override val coroutineContext: CoroutineContext
        get()= job+Dispatchers.Main //arkplanda iş yapılıp maine dönülücek


    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }








}