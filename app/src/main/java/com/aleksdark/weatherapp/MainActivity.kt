package com.aleksdark.weatherapp

import android.Manifest
import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.aleksdark.weatherapp.presentation.CityWeatherFragment.CityWeatherView
import com.aleksdark.weatherapp.repostory.WService
import com.location.aravind.getlocation.GeoLocator
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mDisposable: Disposable? = null
    var latitude: String? = null
    var longtitude: String? = null
    var mWeatherService : WService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPermissions()

        mWeatherService = App.WeatherService


        bottom_item_paris.setOnClickListener {
            goToFragment(CityWeatherView.newInstance("Paris"))
        }

        bottom_item_london.setOnClickListener {
            goToFragment(CityWeatherView.newInstance("London"))
        }

        bottom_item_newyork.setOnClickListener {
            goToFragment(CityWeatherView.newInstance("New York"))
        }

        bottom_item_tokyo.setOnClickListener {
            goToFragment(CityWeatherView.newInstance("Tokyo"))
        }

        bottom_item_current.setOnClickListener {
            goToFragment(CityWeatherView.newInstance("$latitude,$longtitude"))
        }

    }

    private fun getPermissions() {
        val mRxPermissions = RxPermissions(this)

        mDisposable = mRxPermissions
            .request(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .subscribe({ granted ->
                if (granted) {
                    getLocation()
                } else {
                    showToast("No Permissions")
                }
            }
            )
    }

    fun goToFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .apply {
                replace(R.id.content_container, fragment)
                commit()
            }
    }

    fun getLocation() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            val location =  locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            latitude = location.latitude.toString()
            longtitude = location.longitude.toString()
        } else{
            val mGeoLocator = GeoLocator(this@MainActivity.applicationContext, this@MainActivity)
            latitude = mGeoLocator.lattitude.toString()
            longtitude = mGeoLocator.longitude.toString()
        }

        goToFragment(CityWeatherView.newInstance("$latitude,$longtitude"))

//        showToast("$latitude $longtitude")
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable?.let {
            mDisposable!!.dispose()
        }
    }
}
