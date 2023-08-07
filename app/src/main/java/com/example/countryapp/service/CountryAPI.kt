package com.example.countryapp.service

import com.example.countryapp.models.Country
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

// https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

interface CountryAPI {
    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries():Single<List<Country>>
}