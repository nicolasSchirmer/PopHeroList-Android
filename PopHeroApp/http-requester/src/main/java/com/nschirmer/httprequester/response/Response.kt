package com.nschirmer.httprequester.response

/**
 * Generic class to handle json responses
 *
 * Warning!
 * The main assumption is that the api will always be returning an array of objects
 * **/
class Response<T> (val data: List<T>)