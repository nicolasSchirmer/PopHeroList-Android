package com.nschirmer.httprequester

/**
 *  Generic listener that accepts any kind of object
 *  [onSuccess] means that the service responded with some list of objects without connection errors
 *  [onFail] means that the service returned null or some connection error occurred
 *  [noInternet] will be called when the phone could't connect with any outside network
 * **/
interface ConnectionListener<T> {
    fun onSuccess(response: List<T>)
    fun onFail(error: String?)
    fun noInternet()
}