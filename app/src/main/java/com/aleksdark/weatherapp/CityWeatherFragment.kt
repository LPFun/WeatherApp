package com.aleksdark.weatherapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_city_weather.*

class CityWeatherFragment : Fragment() {

    var longitude : String? = null
    var latitude : String? = null

    companion object {
        val LAT = "LAT"
        val LON = "LON"
        fun newInstance(latitude: String?, longituge: String?): Fragment {
            val bundle = Bundle()
            bundle.putString(LAT, latitude)
            bundle.putString(LON, longituge)
            val cityWeatherFragment = CityWeatherFragment()
            cityWeatherFragment.arguments = bundle
            return cityWeatherFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        bundle?.let {
            longitude = bundle.getString(LON)
            latitude = bundle.getString(LAT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container!!.inflate(R.layout.fragment_city_weather)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.text = latitude+longitude
    }

}