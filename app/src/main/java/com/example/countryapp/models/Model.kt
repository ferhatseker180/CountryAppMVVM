package com.example.countryapp.models

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val countryName : String?,
    @SerializedName("capital")
    val countryCapital : String?,
    @SerializedName("region")
    val countryRegion : String?,
    @SerializedName("currency")
    val countryCurrency : String?,
    @SerializedName("flag")
    val flagUrl : String?,
    @SerializedName("language")
    val language : String?)

