package com.aleksdark.weatherapp.presentation.CityWeatherFragment

import android.util.Log
import com.aleksdark.weatherapp.App
import com.aleksdark.weatherapp.presentation.mvp.PresenterBase
import com.aleksdark.weatherapp.repostory.model.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityWeatherPresenter : PresenterBase<Contract.View>(), Contract.Presenter<Contract.View> {

    val TAG = this::class.java.simpleName

    override fun onArgsIsLoaded(city: String) {
        App
            .WeatherService
            .getCurrentWeather("0b7c12973b854be38ec193220181812", city, "ru")
            .enqueue(object : Callback<Weather>{
                override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                    if (response.isSuccessful){
                        val weather : Weather? = response.body()
                        Log.i(TAG,  "${weather!!.current!!.tempC}")
                        view!!.showCurrentWeather(weather)
                    }else{
                        Log.e(TAG, "Error")
                    }
                }

                override fun onFailure(call: Call<Weather>, t: Throwable) {
                    Log.e(TAG, "Error", t)
                }


            })

    }
    override fun viewIReady() {

    }
}