package com.nschirmer.responseobjects

import com.google.gson.annotations.SerializedName

class Year (
    @SerializedName("path") val yearPath: String
) {

    /**
     * @return only the year from the year path (don't send ".json")
     * **/
    fun getOnlyYear(): String {
        return yearPath.substringBefore(".")
    }

}