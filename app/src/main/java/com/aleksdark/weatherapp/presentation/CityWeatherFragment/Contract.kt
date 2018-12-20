package com.aleksdark.weatherapp.presentation.CityWeatherFragment

import com.aleksdark.weatherapp.presentation.mvp.MvpView
import com.aleksdark.weatherapp.repostory.model.Current
import com.aleksdark.weatherapp.repostory.model.Weather

interface Contract{
    interface View : MvpView{
        fun showCurrentWeather(weather: Weather)
    }

    interface Presenter<V : View>{
        fun onArgsIsLoaded(city:String)
    }
}