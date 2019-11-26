package com.nschirmer.poplist.yearselector

import com.nschirmer.httprequester.ConnectionListener
import com.nschirmer.poplist.PopListActivity
import com.nschirmer.responseobjects.Year
import com.nschirmer.webapi.years.YearsApi
import kotlinx.android.synthetic.main.activity_pop_list.*
import kotlinx.android.synthetic.main.activity_pop_list.view.*


/**
 * [yearPath] is the [Year.yearPath] need to request a pop hero list.
 * **/
internal typealias YearPathListener = (yearPath: String) -> Unit


/**
 * This class handles all the year selection. Makes the API call, filter it and handle the[yearselector].
 *
 * @param yearPathListener is a listener that listens the year switch on the [yearselector].
 * **/
internal class YearSelectorHandler(private val activity: PopListActivity, private val yearPathListener: YearPathListener) {

    init {
        getYearsList()
    }

    /**
     * Request the years available on the BitBucket repository.
     * **/
    private fun getYearsList(){
        YearsApi(activity).getAllYearsPaths(object : ConnectionListener<Year> {
            override fun onSuccess(response: List<Year>) {
                setYearSelector(getYearListFiltered(response), response)
            }

            override fun onFail(error: String?) {
                activity.yearSelectorMessaging.errorOccurred(error)
                activity.loadingScreenHandler.showLoading(false)
            }

            override fun noInternet() {
                activity.yearSelectorMessaging.hasNoInternet()
                activity.loadingScreenHandler.showLoading(false)
            }
        })
    }


    /**
     * @param yearsList a list with [Year] from [YearsApi] response.
     * @return a list of strings representing only the years to be shown.
     * **/
    private fun getYearListFiltered(yearsList: List<Year>): List<String> {
        val listFiltered = arrayListOf<String>()
        yearsList.forEach { listFiltered.add(it.getOnlyYear()) }
        return listFiltered
    }


    /**
     * @param years list of only years to be shown.
     * @param responseYearsList the list of [Year] objects that came from [YearsApi].
     * **/
    private fun setYearSelector(years: List<String>, responseYearsList: List<Year>){
        activity.yearselector.setYearLabels(years) { position ->
            // get year path to get a popularity hero list
            yearPathListener(responseYearsList[position].yearPath)
        }
    }

}