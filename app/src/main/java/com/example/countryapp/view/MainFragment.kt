package com.example.countryapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countryapp.R
import com.example.countryapp.adapter.CountryAdapter
import com.example.countryapp.viewmodel.MainFragmentViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    private lateinit var viewModel: MainFragmentViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this@MainFragment).get(MainFragmentViewModel::class.java)

        viewModel.refreshData()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = countryAdapter

     /*   detayaGitButton.setOnClickListener {
            val nav1 = MainFragmentDirections.actionMainFragmentToDetailFragment()
            Navigation.findNavController(it).navigate(nav1)
           // Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_detailFragment)
        }

      */

    }

    fun observeLiveData(){
        viewModel.countries.observe(this, Observer {countries ->
            countries?.let {
                recyclerView.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        })
    }

}