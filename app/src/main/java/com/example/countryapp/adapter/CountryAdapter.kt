package com.example.countryapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.R
import com.example.countryapp.databinding.ItemCountryRowBinding
import com.example.countryapp.models.Country
import com.example.countryapp.util.downloadFromUrl
import com.example.countryapp.util.placeHolderProgressBar
import com.example.countryapp.view.MainFragmentDirections
import kotlinx.android.synthetic.main.item_country_row.view.*

class CountryAdapter(val countryList : ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),CountryClickListener {

    class CountryViewHolder(var view : ItemCountryRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemCountryRowBinding>(inflater,R.layout.item_country_row,parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.view.country = countryList[position]
        holder.view.listener = this

    }

    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val uuid = v.countryUuid.text.toString().toInt()
        val action = MainFragmentDirections.actionMainFragmentToDetailFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}