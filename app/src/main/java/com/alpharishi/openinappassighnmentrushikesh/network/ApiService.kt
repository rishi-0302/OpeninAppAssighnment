package com.alpharishi.openinappassighnmentrushikesh.network


import com.alpharishi.openinappassighnmentrushikesh.model.MainDataClass
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/v1/dashboardNew")
    fun getData(): Call<MainDataClass>
}
