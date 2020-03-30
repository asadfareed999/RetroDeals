package com.example.asadfareed.retrodealsdemo.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.asadfareed.retrodealsdemo.API
import com.example.asadfareed.retrodealsdemo.model.Deal
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DealsViewModel : ViewModel() {
    private  var dealsList: MutableLiveData<ArrayList<Deal>>
    private lateinit var retrofit: Retrofit

    init {
        dealsList= MutableLiveData()
        getRetrofitInstance()
        loadDeals()
        Log.i("Deals View Model"," Deals View Model Created....")
    }

    fun getDeals(): MutableLiveData<ArrayList<Deal>> {
       return dealsList
    }
    private fun loadDeals() {
        val api: API = retrofit.create(API::class.java)
        val call: Call<ArrayList<Deal>> = api.deals
        call.enqueue(object : Callback<ArrayList<Deal>> {
            override fun onResponse(call: Call<ArrayList<Deal>>, response: Response<ArrayList<Deal>>) {
                //finally we are setting the list to our MutableLiveData
                Log.i("Response","Response : "+response.code())
                Log.i("Response","Response : "+response.body())
                dealsList.setValue(response.body())
            }
            override fun onFailure(call: Call<ArrayList<Deal>>, t: Throwable) {
            }
        })
    }

    private fun getRetrofitInstance() {
            val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            httpClient.addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("x-api-key", "5f7af37cb35f5cd8")
                    .build()
                chain.proceed(request)
            }
            retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API.BASE_URL)
                .client(httpClient.build())
                .build()
    }
}