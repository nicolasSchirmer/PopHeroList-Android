package com.nschirmer.webapi.heropop

import android.content.Context
import com.nschirmer.httprequester.ConnectionListener
import com.nschirmer.responseobjects.Hero
import com.nschirmer.webapi.BuildConfig.*
import com.nschirmer.webapi.CommonApi

class HeroApi (private val context: Context) {

    private val serviceApi by lazy {
        CommonApi(context, PATH_API, HeroApiCall::class.java).serviceApi
    }


    /**
     * @param yearPath is the json file path of a given year through [YearsApi]
     * @param connectionListener return the list of [Hero], an error or a warning that there is no internet.
     * **/
    fun getHeroPop(yearPath: String, connectionListener: ConnectionListener<Hero>){
        serviceApi.callServerApi(serviceApi.clientApi.getHeroPop(yearPath), object : ConnectionListener<Hero> {
            override fun onSuccess(response: List<Hero>) {
                // add the full url to the image path
                connectionListener.onSuccess(
                    addFullUrlToHeroes(response)
                )
            }

            override fun onFail(error: String?) { connectionListener.onFail(error) }
            override fun noInternet() { connectionListener.noInternet() }
        })
    }


    /**
     * @param heroes is the list of [Hero] that not contain all the picture URL
     * @return a list of [Hero] that contain all URL path to picture variable
     * **/
    private fun addFullUrlToHeroes(heroes: List<Hero>): List<Hero>{
        val refinedList = arrayListOf<Hero>()

        heroes.forEach {

            refinedList.add(
                // need to create a new object due to val
                Hero(it.name,
                BASE_URL + PATH_IMAGE + renameToJpg(it.pictureUrl),
                    it.score)
            )
        }

        return refinedList
    }


    /**
     * @param pictureUrl is the [Hero.pictureUrl] that came straight from the API.
     * @return the [pictureUrl] but instead of .jpeg is .jpg now.
     * **/
    private fun renameToJpg(pictureUrl: String): String {
        return pictureUrl.substringBefore(".") + ".jpg"
    }

}