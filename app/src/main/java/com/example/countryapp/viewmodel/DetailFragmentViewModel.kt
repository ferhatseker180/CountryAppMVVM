package com.example.countryapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countryapp.models.Country
import com.example.countryapp.service.CountryDatabase
import kotlinx.coroutines.launch

class DetailFragmentViewModel(application: Application) : BaseViewModel(application) {
    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(uuid : Int){

        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
          val country = dao.getCountry(uuid)
            countryLiveData.value = country
        }
    }

}