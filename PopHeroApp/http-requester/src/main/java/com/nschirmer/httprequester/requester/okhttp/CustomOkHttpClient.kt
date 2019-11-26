package com.nschirmer.httprequester.requester.okhttp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

internal class CustomOkHttpClient {

    /** @return OkHttpClient with [ChangeObjectNameInterceptor] **/
    val okHttpClient: OkHttpClient by lazy {

        // Log interceptor
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder().let { clientBuilder ->
            clientBuilder.addInterceptor(interceptor)
            clientBuilder.addInterceptor(ChangeObjectNameInterceptor())
            clientBuilder.build()
        }
    }

}