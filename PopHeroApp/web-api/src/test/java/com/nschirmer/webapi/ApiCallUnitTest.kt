package com.nschirmer.webapi

import android.content.Context
import com.nschirmer.httprequester.ConnectionListener
import com.nschirmer.responseobjects.Hero
import com.nschirmer.responseobjects.Year
import com.nschirmer.webapi.heropop.HeroApi
import com.nschirmer.webapi.years.YearsApi
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.InjectMocks
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


internal class ApiCallUnitTest {


    @Mock val context: Context? = null
    @InjectMocks val heroApi: HeroApi? = null
    @InjectMocks val yearApi: YearsApi? = null


    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun heroCallTest() {
        heroApi?.getHeroPop("2006.json", object : ConnectionListener<Hero> {
            override fun onSuccess(response: List<Hero>) {
                val a = response
            }
            override fun onFail(error: String?) {
                val e = error
            }
            override fun noInternet() {

            }
        })
    }

    @Test
    fun yearCallTest(){
        yearApi?.getAllYearsPaths(object : ConnectionListener<Year> {
            override fun onSuccess(response: List<Year>) {
                val a = response
            }
            override fun onFail(error: String?) {
                val e = error
            }
            override fun noInternet() {

            }
        })
    }

}
