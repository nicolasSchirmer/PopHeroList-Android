package com.nschirmer.webapi.heropop

import com.nschirmer.httprequester.response.Response
import com.nschirmer.responseobjects.Hero
import com.nschirmer.responseobjects.Year
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface HeroApiCall {

    /**
     * @param jsonPath is the json file path of the repository that is retrieved with [YearsApi]
     * @return a [Call] of the HTTP request with a [Hero] object.
     * **/

    @GET ("{jsonPath}")
    fun getHeroPop(@Path("jsonPath") jsonPath: String): Call<Response<Hero>>

}