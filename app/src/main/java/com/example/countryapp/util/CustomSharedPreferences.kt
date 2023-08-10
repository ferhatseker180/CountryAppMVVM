package com.example.countryapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class CustomSharedPreferences {

    companion object {

        private var sharedPreferences : SharedPreferences? = null

        @Volatile private var instance : CustomSharedPreferences? = null

        private val lock = Any()

        operator fun invoke(context: Context) : CustomSharedPreferences = instance ?: synchronized(lock){
            instance ?: makeSharedPref(context).also {
                instance = it
            }
        }


        private fun makeSharedPref(context: Context) : CustomSharedPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }

    }
}