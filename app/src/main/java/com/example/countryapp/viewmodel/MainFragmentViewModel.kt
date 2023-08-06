package com.example.countryapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countryapp.models.Country

class MainFragmentViewModel : ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val country = Country("Turkey","Ankara","Asian And Europa","TL","https:","Turkish")
        val country2 = Country("France","Paris","Europa","EURO","https:","French")

        val countryList = arrayListOf<Country>(country,country2)

        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }

}