package com.example.countryapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countryapp.models.Country
import com.example.countryapp.service.CountryAPIService
import com.example.countryapp.service.CountryDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class MainFragmentViewModel(application: Application) : BaseViewModel(application) {

    private val countryApiService = CountryAPIService()
    private val disposable = CompositeDisposable()
    // Composite Disposible: Hafızayı verimli kullanmayı sağlar. Birden fazla kol başka bir fragmenta vs geçerken arkada kalmadan yok edilir.

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        getDataFromAPI()
    }

    private fun getDataFromAPI(){

        countryLoading.value = true
        disposable.add(
            countryApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){

                    override fun onSuccess(t: List<Country>) {
                        storeInSQLite(t)
                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryError.value = true
                        e.printStackTrace()
                    }

                })
        )

    }

    private fun showCountries(countryList : List<Country>){
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }

    private fun storeInSQLite(list : List<Country>){

        launch {
            val dao = CountryDatabase(getApplication()).countryDao()

            dao.deleteAllCountries()
            // Daha önceden kalan verileri silerek başlayalım.
          val listLong = dao.insertAll(*list.toTypedArray())
            // toTypedArray listeyi tekil hale getirir yani teker teker eleman haline gelir.
            var i = 0
            while (i < list.size){
                list[i].uuid = listLong[i].toInt()
                i = i+1
            }
            showCountries(list)

        }
    }

}