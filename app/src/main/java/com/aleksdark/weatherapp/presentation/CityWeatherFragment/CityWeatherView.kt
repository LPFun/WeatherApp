package com.aleksdark.weatherapp.presentation.CityWeatherFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aleksdark.weatherapp.R
import com.aleksdark.weatherapp.inflate
import com.aleksdark.weatherapp.repostory.model.weather.Weather
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_city_weather.*

class CityWeatherView : Fragment(), Contract.View {


    var city: String? = null
    lateinit var mPresenter: CityWeatherPresenter

    companion object {
        val CITY = "CITY"
        fun newInstance(city: String?): Fragment {
            val bundle = Bundle()
            bundle.putString(CITY, city)
            val cityWeatherFragment = CityWeatherView()
            cityWeatherFragment.arguments = bundle
            return cityWeatherFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        retainInstance = true
        super.onCreate(savedInstanceState)
        mPresenter = CityWeatherPresenter()
        val bundle = arguments

        bundle?.let {
            city = bundle.getString(CITY)
            mPresenter.onArgsIsLoaded(city)
        }

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
        weather_error_lay.visibility = View.GONE
        weather_city.text = "Location: ${weather.location!!.name}"
        weather_humidity.text = "Humidity (%): ${weather.current!!.humidity.toString()}"
        weather_temp.text = "Temperature (C): ${weather.current!!.tempC.toString()}"
        weather_wind.text = "Wind Speed (km/h): ${weather.current!!.windKph.toString()}"
        weather_description.text = weather.current!!.condition!!.text
        val icon_uri = weather.current!!.condition!!.icon!!
        Picasso.get().load("http://${icon_uri.substring(1)}").into(weather_img)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (isRemoving)
            mPresenter.destroy()
        mPresenter.detachView()
    }

    override fun showError(msg: String) {
        weather_error_lay.visibility = View.VISIBLE
        weather_error_txt.text = msg
    }


}