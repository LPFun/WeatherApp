package com.aleksdark.weatherapp

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import com.location.aravind.getlocation.GeoLocator
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mDisposable: Disposable? = null
    var latitude: String? = null
    var longtitude: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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


        bottom_item_paris.setOnClickListener {
            goToFragment(CityWeatherFragment.newInstance(latitude, longtitude))
        }

        bottom_item_london.setOnClickListener {
            goToFragment(CityWeatherFragment.newInstance(latitude, longtitude))
        }

        bottom_item_newyork.setOnClickListener {
            goToFragment(CityWeatherFragment.newInstance(latitude, longtitude))
        }

        bottom_item_tokyo.setOnClickListener {
            goToFragment(CityWeatherFragment.newInstance(latitude, longtitude))
        }

        bottom_item_current.setOnClickListener {
            goToFragment(CityWeatherFragment.newInstance(latitude, longtitude))
        }

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
        val mGeoLocator = GeoLocator(applicationContext, this)
        latitude = mGeoLocator.lattitude.toString()
        longtitude = mGeoLocator.longitude.toString()
        showToast("$latitude $longtitude")
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
