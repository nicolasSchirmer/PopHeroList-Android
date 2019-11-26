package com.nschirmer.poplist

import android.content.Context
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations


internal class PopActivityUnitTest {

    @Mock internal var context: Context? = null
    @InjectMocks internal var popListActivity: PopListActivity? = null

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun displayMessage(){
        popListActivity?.yearSelectorMessaging?.setMessage("Test")
    }

    @Test
    fun displayError(){
        popListActivity?.yearSelectorMessaging?.errorOccurred("Error")
    }

    @Test
    fun displayNoNet(){
        popListActivity?.yearSelectorMessaging?.hasNoInternet()
    }

    @Test
    fun displayLoading(){
        popListActivity?.yearSelectorMessaging?.loading()
    }

    @Test
    fun displayLoadingScreen(){
        popListActivity?.loadingScreenHandler?.showLoading(true)
    }

    @Test
    fun removeLoadingScreen(){
        popListActivity?.loadingScreenHandler?.showLoading(false)
    }


}
