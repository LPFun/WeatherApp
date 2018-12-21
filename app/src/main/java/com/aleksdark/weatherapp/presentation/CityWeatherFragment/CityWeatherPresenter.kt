package com.aleksdark.weatherapp.presentation.CityWeatherFragment

import com.aleksdark.weatherapp.presentation.mvp.PresenterBase
import com.aleksdark.weatherapp.repostory.Repository

class CityWeatherPresenter : PresenterBase<Contract.View>(), Contract.Presenter<Contract.View> {

    val TAG = this::class.java.simpleName
    var city: String? = null
    val repo = Repository()

    override fun onArgsIsLoaded(city: String?) {
        this.city = city

    }
    override fun viewIReady() {
        if (city != null){
            repo.getWeather(
                city!!,
                {w -> view?.let {  view!!.showCurrentWeather(w!!)}
                },
                {
                        error -> view?.let { view!!.showError(error) }
                })
        } else
            view?.let { view!!.showError("No Location") }

    }
}
