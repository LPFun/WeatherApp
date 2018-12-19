package com.aleksdark.weatherapp.presentation.CityWeatherFragment

import com.aleksdark.weatherapp.presentation.mvp.PresenterBase

class CityWeatherPresenter : PresenterBase<Contract.View>(), Contract.Presenter<Contract.View> {
    override fun onArgsIsLoaded(lat: String?, longt: String?) {

    }
    override fun viewIReady() {

    }
}