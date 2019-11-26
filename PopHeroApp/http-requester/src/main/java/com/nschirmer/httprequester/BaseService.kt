package com.nschirmer.httprequester

import android.content.Context
import com.nschirmer.httprequester.response.Response
import com.nschirmer.networkchecker.NetworkChecker
import com.nschirmer.httprequester.requester.retrofit.Callback
import com.nschirmer.httprequester.requester.retrofit.RequestClient
import retrofit2.Call

/** This class is an abstraction of service choosing with [RequestClient], so that any Service to use **/
class BaseService<T> (private val context: Context, private val baseApiUrl: String, private val serviceContract: Class<T>) {

    /** A ready to use retrofit object based on the service contract **/
    val clientApi by lazy {
        RequestClient(baseApiUrl).retrofit.create(serviceContract)
    }


    /**
     * Abstraction of [Callback] that already check if there is internet.
     * @param call expect the [Call] object generated from the service contract.
     * @param connectionListener is a pass through of the rest call state @see [ConnectionListener].
     * **/
    fun <R> callServerApi(call : Call<Response<R>>, connectionListener: ConnectionListener<R>) {
        NetworkChecker(context){ canConnectToInternet, _ ->
            if(canConnectToInternet) call.enqueue(Callback(context, connectionListener))
            else connectionListener.noInternet()
        }
    }

}