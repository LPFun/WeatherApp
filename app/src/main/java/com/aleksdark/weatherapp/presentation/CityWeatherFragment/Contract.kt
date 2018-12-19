package com.aleksdark.weatherapp.presentation.CityWeatherFragment

import com.aleksdark.weatherapp.presentation.mvp.MvpView

interface Contract{
    interface View : MvpView{

    }

    interface Presenter<V : View>{
        fun onArgsIsLoaded(lat:String?, longt:String?)
    }
}