package com.nschirmer.httprequester.requester.retrofit

import com.google.gson.GsonBuilder
import com.nschirmer.httprequester.requester.okhttp.CustomOkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


internal class RequestClient(private val baseURL: String) {

    /** Retrofit with custom [GsonConverterFactory] with the server date format and [CustomOkHttpClient] **/
    val retrofit: Retrofit by lazy {
        Retrofit.Builder().let {
            it.baseUrl(baseURL)
            it.addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd")
                .create()))
            it.client(CustomOkHttpClient().okHttpClient)
            it.build()
        }
    }

}