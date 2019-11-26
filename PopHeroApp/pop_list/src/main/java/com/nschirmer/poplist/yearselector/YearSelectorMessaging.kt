package com.nschirmer.poplist.yearselector

import com.nschirmer.poplist.PopListActivity
import com.nschirmer.poplist.R
import kotlinx.android.synthetic.main.activity_pop_list.*

/**
 * Messaging class to display into years selector label area.
 * **/
internal class YearSelectorMessaging(private val activity: PopListActivity) {

    fun errorOccurred(error: String?){
        clearSelector()
        setMessage(R.string.error)
    }


    fun hasNoInternet(){
        clearSelector()
        setMessage(R.string.nonet)
    }


    fun loading(){
        setMessage(R.string.loading)
    }


    fun setMessage(messageRes: Int){
        setMessage(activity.getString(messageRes))
    }


    fun setMessage(message: String){
        activity.runOnUiThread {
            activity.yearselector.yearLabel = message
        }
    }


    private fun clearSelector(){
        activity.runOnUiThread {
            activity.yearselector.clearEverything()
        }
    }

}