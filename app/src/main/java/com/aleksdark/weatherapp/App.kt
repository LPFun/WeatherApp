package com.aleksdark.weatherapp

import android.app.Application

import com.aleksdark.weatherapp.repostory.RetrofitClient
import com.aleksdark.weatherapp.repostory.WService

class App : Application() {

    companion object {
        val API_URL = "http://api.apixu.com/v1/"
        val WeatherService:WService = RetrofitClient.getClient(API_URL)!!.create(WService::class.java)
    }

    override fun onCreate() {
        super.onCreate()
    }
}