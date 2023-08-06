package com.example.countryapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.countryapp.R

class MainFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

     /*   detayaGitButton.setOnClickListener {
            val nav1 = MainFragmentDirections.actionMainFragmentToDetailFragment()
            Navigation.findNavController(it).navigate(nav1)
           // Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_detailFragment)
        }

      */

    }

}