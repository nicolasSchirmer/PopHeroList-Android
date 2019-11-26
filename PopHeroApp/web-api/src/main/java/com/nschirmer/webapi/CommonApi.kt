package com.nschirmer.webapi

import android.content.Context
import com.nschirmer.httprequester.BaseService

internal class CommonApi<T> (private val context: Context, private val pathUrl: String, private val apiCallClass: Class<T>){

    val serviceApi by lazy {
        BaseService(context, BuildConfig.BASE_URL + pathUrl, apiCallClass)
    }

}