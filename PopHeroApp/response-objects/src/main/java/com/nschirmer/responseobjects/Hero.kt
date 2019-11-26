package com.nschirmer.responseobjects

import com.google.gson.annotations.SerializedName


class Hero (
    @SerializedName ("Name") val name: String,
    @SerializedName ("Picture") val pictureUrl: String,
    @SerializedName ("Score") val score: String
)