package com.aleksdark.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_item_paris.setOnClickListener {
            showToast("Paris")
        }

        bottom_item_london.setOnClickListener {
            showToast("London")
        }

        bottom_item_newyork.setOnClickListener {
            showToast("New-York")
        }

        bottom_item_tokyo.setOnClickListener {
            showToast("Tokyo")
        }

        bottom_item_current.setOnClickListener {
            showToast("Current")
        }

    }

    fun showToast(msg : String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
