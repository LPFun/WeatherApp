package com.aleksdark.weatherapp.repostory

import android.util.Log
import com.aleksdark.weatherapp.App
import com.aleksdark.weatherapp.repostory.model.weather.Weather
import com.androidfung.geoip.GeoIpService
import com.androidfung.geoip.ServicesManager
import com.androidfung.geoip.model.GeoIpResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    fun getWeather(city: String, success: (w: Weather?) -> Unit, failed: (t: String) -> Unit) {
        App
            .WeatherService
            .getCurrentWeather("0b7c12973b854be38ec193220181812", city)
            .enqueue(object : Callback<Weather> {
                override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                    if (response.isSuccessful) {
                        val weather: Weather? = response.body()
                        success(weather)
                    } else {
                        failed("Error")
                    }
                }

                override fun onFailure(call: Call<Weather>, t: Throwable) {
                    failed("No Internet")
                }
            })
    }

    fun getCurrentLocation(success: (city: String?) -> Unit, failed: (f: String) -> Unit) {
        val ipApiService = ServicesManager.getGeoIpService();
        ipApiService
            .getGeoIp()
            .enqueue(object : Callback<GeoIpResponseModel> {

                override fun onResponse(call: Call<GeoIpResponseModel>, response: Response<GeoIpResponseModel>) {
                    if (response.code() == 200){
                        if (response.isSuccessful)
                            success(response.body()!!.city)
                    } else{
                        failed("No Internet")
                    }

                }

                override fun onFailure(call: Call<GeoIpResponseModel>, t: Throwable) {
                    success(null)
                    failed("No Internet")
                }
            })
    }
}