package com.aleksdark.weatherapp.presentation.CityWeatherFragment

import com.aleksdark.weatherapp.presentation.mvp.MvpView
import com.aleksdark.weatherapp.repostory.model.weather.Weather

interface Contract {
    interface View : MvpView {
        fun showCurrentWeather(weather: Weather)
        fun showError(msg: String)
    }

    interface Presenter<V : View> {
        fun onArgsIsLoaded(city: String?)
    }
}