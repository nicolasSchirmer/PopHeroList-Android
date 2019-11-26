package com.nschirmer.poplist

import android.os.Bundle
import com.nschirmer.pophero.util.BaseActivity
import com.nschirmer.poplist.herolist.HeroPopListHandler
import com.nschirmer.poplist.yearselector.YearSelectorHandler
import com.nschirmer.poplist.yearselector.YearSelectorMessaging

open class PopListActivity : BaseActivity() {

    internal val loadingScreenHandler by lazy { LoadingScreenHandler(this) }
    internal val yearSelectorMessaging by lazy { YearSelectorMessaging(this) }
    private val heroPopListHandler by lazy { HeroPopListHandler(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_list)

        loading()
        setYearSelector()
    }

    private fun loading(){
        yearSelectorMessaging.loading()
        loadingScreenHandler.showLoading(true)
    }

    private fun setYearSelector(){
        YearSelectorHandler(this) { yearPath ->
            setHeroPopList(yearPath)
        }
    }


    private fun setHeroPopList(yearPath: String){
        heroPopListHandler.setListFromYearPath(yearPath)
    }

}
