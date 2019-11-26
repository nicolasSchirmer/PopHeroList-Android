package com.nschirmer.poplist.herolist

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.nschirmer.httprequester.ConnectionListener
import com.nschirmer.poplist.PopListActivity
import com.nschirmer.responseobjects.Hero
import com.nschirmer.webapi.heropop.HeroApi
import kotlinx.android.synthetic.main.hero_list.*


internal class HeroPopListHandler(private val activity: PopListActivity) {

    // list used for the recycler view
    private val heroPopList = arrayListOf<Hero>()
    private val listAdapter = HeroPopListAdapter(heroPopList)

    init {
        setRecyclerList()
    }


    private fun setRecyclerList(){
        activity.hero_list.let {
            it.layoutManager = LinearLayoutManager(activity)
            it.adapter = listAdapter
        }
    }


    fun setListFromYearPath(yearPath: String){
        activity.loadingScreenHandler.showLoading(true)
        HeroApi(activity).getHeroPop(yearPath, object : ConnectionListener<Hero> {
            override fun onSuccess(response: List<Hero>) {
                if(! activity.hero_list.isVisible) activity.hero_list.visibility = View.VISIBLE
                updateList(getHeroListSortedByScore(response))
                activity.loadingScreenHandler.showLoading(false)
            }

            override fun onFail(error: String?) {
                activity.yearSelectorMessaging.errorOccurred(error)
                apiCallFailed()
            }

            override fun noInternet() {
                activity.yearSelectorMessaging.hasNoInternet()
                apiCallFailed()
            }
        })
    }


    private fun apiCallFailed(){
        activity.loadingScreenHandler.showLoading(false)
        activity.runOnUiThread {
            activity.hero_list.visibility = View.GONE
        }
    }


    /**
     * @param heroesList is the list of heroes not sorted from [HeroApi].
     * @return descend sorted list using [Hero.score] as parameter to sort.
     * **/
    private fun getHeroListSortedByScore(heroesList: List<Hero>): List<Hero> {
        return heroesList.sortedWith(compareByDescending{it.score.toInt()})
    }


    private fun updateList(newList: List<Hero>){
        heroPopList.clear()
        heroPopList.addAll(newList)
        listAdapter.notifyDataSetChanged()
    }

}