package com.example.asadfareed.retrodealsdemo

import com.example.asadfareed.retrodealsdemo.model.Deal
import retrofit2.Call
import retrofit2.http.GET

interface API {

    @get:GET("deals")
    val deals: Call<ArrayList<Deal>>

    companion object {
        val BASE_URL = "http://52.73.221.180/api/v1/"
    }
}