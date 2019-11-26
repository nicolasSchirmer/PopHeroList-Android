package com.nschirmer.widgets

import android.content.Context
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Mock val context: Context? = null
    @InjectMocks val yearSelector: YearSelector? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun setLabelsTest() {
        val list = arrayListOf<String>()
        list.add("1")
        list.add("2")

        yearSelector?.setYearLabels(list, object : YearSelectorListener {
            override fun invoke(position: Int) {
                val a = position
            }
        })
    }

    @Test
    fun cleanSelectorTest() {
        yearSelector?.clearEverything()
    }
}
