package com.aleksdark.weatherapp.presentation.CityWeatherFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aleksdark.weatherapp.R
import com.aleksdark.weatherapp.inflate
import com.aleksdark.weatherapp.repostory.model.Current
import com.aleksdark.weatherapp.repostory.model.Weather
import kotlinx.android.synthetic.main.fragment_city_weather.*

class CityWeatherView : Fragment(), Contract.View {


    var city : String? = null
    lateinit var mPresenter: CityWeatherPresenter

    companion object {
        val CITY = "LAT"
        fun newInstance(city: String): Fragment {
            val bundle = Bundle()
            bundle.putString(CITY, city)
            val cityWeatherFragment = CityWeatherView()
            cityWeatherFragment.arguments = bundle
            return cityWeatherFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = CityWeatherPresenter()
        val bundle = arguments
        bundle?.let {
            city = bundle.getString(CITY)
        }
        mPresenter.onArgsIsLoaded(city!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container!!.inflate(R.layout.fragment_city_weather)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.attachView(this)
        mPresenter.viewIReady()
    }

    override fun showCurrentWeather(weather: Weather) {
        weather_city.text = weather.location!!.name
        weather_humidity.text = weather.current!!.humidity.toString()
        weather_temp.text = weather.current!!.tempC.toString()
        weather_wind.text = weather.current!!.windKph.toString()
        weather_description.text = weather.current!!.condition!!.text
    }

}