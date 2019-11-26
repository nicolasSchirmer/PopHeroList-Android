package com.nschirmer.httprequester.requester.okhttp

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody


/**
 * Custom [Interceptor] due to server response not complying with the [com.nschirmer.webservice.requestapi.response.Response.data]
 * serialized name.
 *
 * The server return the object name as json root key object field.
 * @example

    # Reponse 1
        {
            "pagelen": {...},
            "values": [{...}, ...]
            ....
        }

    # Reponse 2
        {
            "Heroes": [{...}, ... ]
        }

 *
 * The app is expecting this kind of response (due to [com.nschirmer.webservice.requestapi.response.Response] structure):
 * @example

    {
        "data": [{...}...]
    }


 * !!!! IMPORTANT !!!!:
 * The key assumption is that the object that the app need is contained into a array.
 *
 * So..... This interceptor will do just that.
 * It will hook the service call and modify the [Response] with the desirable data structure.
 *
 * This is necessary because, unlike Jackson json library, [com.google.gson.Gson] doesn't have a dynamic serial name and
 * due to how the [com.nschirmer.webservice.requestapi.retrofit.Callback] generic approach, it is impossible to convert
 * naturally with [com.google.gson.Gson].
 *
 * The Jackson is not implemented due to [com.google.gson.Gson] widely adoption and easy to use.
 * [ChangeObjectNameInterceptor] exists to minimize the dependency count.
 * **/
internal class ChangeObjectNameInterceptor: Interceptor {

    private companion object {
        const val KEY_NAME = "data"
        const val KEY_PATTERN = "\"$KEY_NAME\":[{\"" // "someWord":[{"  pattern

        /**
         * Seek the json array object key pattern name and replace it with [KEY_PATTERN]
         *
         * @return the modified body with the [KEY_NAME] replacing the object key name.
         *
         * Warning:
         * If in any time the response data structure changes to something completely different, it will not be able to adapt.
         *
         * This method will adapt itself if the first json object has anything or "data" key name.
         * It will NOT change any other objects key names.
         *
         *
         * Warning 2:
         * The [responseBody] getString method only allow to be called two times before wipe itself from the memory buffer.
         * @see [https://github.com/square/okhttp/issues/2869]
         *
         * And guess what.... [retrofit2.converter.gson.GsonResponseBodyConverter] Already consume the body one time with the charStream.
         * You only have one shot to change the response body :)
         * **/
        private fun getBodyWithModifiedKeyName(responseBody: ResponseBody?): String {
            val regex = """(?:"(\w+)":)(\s*)(?:\[)(\s*)(?:\{)(\s*)(?:")""".toRegex() // "someWord":[{"  pattern
            return responseBody?.string()?.replaceFirst(regex, KEY_PATTERN) ?: "null"
        }
    }


    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val rawJson = getBodyWithModifiedKeyName(response.body)


        // Because the server does not have a unique identifier to the data object and the
        // service solution is generic, this is necessary until the response changes
        return response.newBuilder().body(rawJson.toResponseBody(response.body?.contentType())).build()
    }


}