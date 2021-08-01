package com.example.dependencyinjection.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherDataCache @Inject constructor(){

    private var weatherData: WeatherData? = null

    fun getData() : WeatherData? {
        return weatherData
    }

    fun setData(weatherData: WeatherData) {
        this.weatherData = weatherData
    }
}
