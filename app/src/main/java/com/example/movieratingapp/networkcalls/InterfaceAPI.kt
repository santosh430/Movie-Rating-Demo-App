package com.example.movieratingapp.networkcalls

import com.example.movieratingapp.data.DataAPIClass
import retrofit2.Call
import retrofit2.http.GET


interface InterfaceAPI {
    @GET("3/trending/all/day?api_key=7bb5feaa0773a3dfa8b671cd729929ef")
    fun getData(): Call<DataAPIClass>
//https://api.themoviedb.org/3/trending/all/day?api_key=7bb5feaa0773a3dfa8b671cd729929ef

}


