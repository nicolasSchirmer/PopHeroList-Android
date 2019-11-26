package com.nschirmer.responseobjects

import org.junit.Test

internal class YearUnitTest {

    /**
     * Check if the [Year.getOnlyYear] is functioning as expected. Trimming down the ".json" from the [Year.yearPath].
     * **/
    @Test
    fun getOnlyYear(): Boolean {
        val yearPathExample = "2019.json"
        val onlyYearExample = "2009"

        val year = Year(yearPathExample)

        val isCorrect = year.getOnlyYear() == onlyYearExample

        return isCorrect
    }

}