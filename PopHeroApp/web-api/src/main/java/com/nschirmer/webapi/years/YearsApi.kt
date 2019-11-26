package com.nschirmer.webapi.years

import android.content.Context
import com.nschirmer.httprequester.ConnectionListener
import com.nschirmer.responseobjects.Year
import com.nschirmer.webapi.BuildConfig.PATH_API
import com.nschirmer.webapi.CommonApi


class YearsApi (private val context: Context) {

    private val serviceApi by lazy {
        CommonApi(context, PATH_API, YearsApiCall::class.java).serviceApi
    }


    /**
     * @param connectionListener return the list of [Year], an error or a warning that there is no internet.
     * **/
    fun getAllYearsPaths(connectionListener: ConnectionListener<Year>){
        serviceApi.callServerApi(serviceApi.clientApi.getYears(), object : ConnectionListener<Year> {
            override fun onSuccess(response: List<Year>) {
                // filter the scrapping junk left
                connectionListener.onSuccess(response.filter {
                    it.yearPath.matches(
                        Regex("\\d{4}(.)(json)")
                    )
                })
            }

            override fun onFail(error: String?) { connectionListener.onFail(error) }
            override fun noInternet() { connectionListener.noInternet() }
        })
    }

}