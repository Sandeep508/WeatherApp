package com.example.weatherapp.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.database.DailyWeather
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject

class WeatherViewModel(val repo : WeatherRepository) : ViewModel() {
    var weather : LiveData<List<DailyWeather>>?
    var job : Job? = null
    var username = ""
    init {
        weather = repo.getDailyWeather()
    }

    //Update the weather from API and put it into the database
    fun updateWeather(latitude : String, longitude : String) {
        /*job = CoroutineScope(Dispatchers.IO).launch {
            var res = repo.getWeather(latitude, longitude)
            if (res.isSuccessful) {
                println(res.body())
            }
        }*/

        repo.updateWeather(latitude, longitude)
    }

    fun getDailyWeather(){
        weather = repo.getDailyWeather()
    }

    fun getDailyWeatherObservable() : Observable<List<DailyWeather>>? {
        return repo.getDailyWeatherObservable()
    }


    //retrofit
    fun getHourlyForecast(latitude : String, longitude : String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            var res = repo.getHourlyForecast(latitude, longitude)
            if (res.isSuccessful) {
                println(res.body())
            }
        }
    }

    fun getDailyForecast(latitude : String, longitude : String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            var res = repo.getDailyForecast(latitude, longitude)
            if (res.isSuccessful) {
                println(res.body())
            }
        }
    }


}