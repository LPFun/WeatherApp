package com.aleksdark.weatherapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.aleksdark.weatherapp.presentation.CityWeatherFragment.CityWeatherView
import com.aleksdark.weatherapp.repostory.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var latitude: String? = null
    var longtitude: String? = null
    var city: String? = null
    var permission: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getLocation()

        initView()
    }

    private fun initView() {
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
            goToFragment(CityWeatherView.newInstance(city))
        }
    }

    private fun getLocation() {
        Repository().getCurrentLocation({
            city = it
            goToFragment(CityWeatherView.newInstance(city))
        }, {
            showToast(it)
        })

    }

    fun goToFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .apply {
                replace(R.id.content_container, fragment)
                commit()
            }
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}
