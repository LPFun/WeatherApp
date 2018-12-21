package com.aleksdark.weatherapp.repostory

import com.aleksdark.weatherapp.repostory.model.weather.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WService {
    @GET("current.json")
    fun  getCurrentWeather(@Query("key") key:String, @Query("q") q:String) : Call<Weather>
}