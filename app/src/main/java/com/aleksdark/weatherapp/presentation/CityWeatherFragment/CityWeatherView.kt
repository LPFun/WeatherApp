package com.aleksdark.weatherapp.presentation.CityWeatherFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aleksdark.weatherapp.R
import com.aleksdark.weatherapp.inflate
import kotlinx.android.synthetic.main.fragment_city_weather.*

class CityWeatherView : Fragment(), Contract.View {

    var longitude : String? = null
    var latitude : String? = null
    lateinit var mPresenter: CityWeatherPresenter

    companion object {
        val LAT = "LAT"
        val LON = "LON"
        fun newInstance(latitude: String?, longituge: String?): Fragment {
            val bundle = Bundle()
            bundle.putString(LAT, latitude)
            bundle.putString(LON, longituge)
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
            longitude = bundle.getString(LON)
            latitude = bundle.getString(LAT)
        }
        mPresenter.onArgsIsLoaded(latitude, longitude)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container!!.inflate(R.layout.fragment_city_weather)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.attachView(this)
        mPresenter.viewIReady()
        textView.text = latitude+longitude
    }

}