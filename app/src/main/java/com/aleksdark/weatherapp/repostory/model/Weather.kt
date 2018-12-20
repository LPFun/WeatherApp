package com.aleksdark.weatherapp.repostory.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Weather {

    @SerializedName("location")
    @Expose
    var location: Location? = null
    @SerializedName("current")
    @Expose
    var current: Current? = null

}