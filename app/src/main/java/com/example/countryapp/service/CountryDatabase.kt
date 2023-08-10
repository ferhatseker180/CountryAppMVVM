package com.example.countryapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.countryapp.models.Country

@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDatabase : RoomDatabase(){

    abstract fun countryDao() : CountryDao

    companion object {
        @Volatile private var instance : CountryDatabase? = null
        private val lock = Any()
        // Volatile : Volatile olarak tanımlanırsa diğer threadlere de görünür hale getirilir.
        // İnvoke instance var mı yok mu onu kontrol eder.
        // synchronized : Aynı anda birden çok thread objeye ulaşamıyor

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }

        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CountryDatabase::class.java,
            "countrydatabase"
        ).build()

    }
}