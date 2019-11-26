package com.nschirmer.webapi.years

import com.nschirmer.httprequester.response.Response
import com.nschirmer.responseobjects.Year
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

internal interface YearsApiCall {

    /**
     * @return a [Call] of the HTTP request with a [Year] object.
     *
     * Attention!
     * This is a web scrapping method, so it will be returning more
     * than years, please run a filter on it before returning it to the caller.
     * **/

    @GET(".")
    fun getYears(): Call<Response<Year>>

}