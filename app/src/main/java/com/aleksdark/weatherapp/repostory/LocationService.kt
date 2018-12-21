package com.aleksdark.weatherapp.repostory

import com.aleksdark.weatherapp.repostory.model.location.CurLoc
import retrofit2.Call
import retrofit2.http.GET

interface LocationService {
    @GET("")
    fun getCurrentLocation() : Call<CurLoc>
}