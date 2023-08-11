package com.example.countryapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.example.countryapp.R
import com.example.countryapp.util.downloadFromUrl
import com.example.countryapp.util.placeHolderProgressBar
import com.example.countryapp.viewmodel.DetailFragmentViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {
    private lateinit var viewModel : DetailFragmentViewModel
    private var countryUuid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

            countryUuid = DetailFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel = ViewModelProviders.of(this@DetailFragment).get(DetailFragmentViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)



        observeLiveData()
    }

   private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                detailCountryName.text = country.countryName
                detailCountryCapital.text = country.countryCapital
                detailCountryRegion.text = country.countryRegion
                detailCountryCurrency.text = country.countryCurrency
                detailCountryLanguage.text = country.language

                context?.let {
                    detailCountryImage.downloadFromUrl(country.flagUrl, placeHolderProgressBar(it))
                }

            }
        })
    }

}