package com.example.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private val weatherConditions = arrayOf(
        WeatherData("Slunečno", "Praha", "25°C", "0%", "3", R.drawable.sun),
        WeatherData("Oblačno", "Slavičín", "20°C", "10%","5", R.drawable.cloudy),
        WeatherData("Bouřky", "Brno","15°C", "80%","2" ,R.drawable.storm),
        WeatherData("Sníh", "Ostrava","-2°C", "100%","1", R.drawable.snow)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            updateWeather()
        }

        binding.weatherIcon.setOnClickListener() { updateWeather() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("weatherIndex", currentWeatherIndex)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentWeatherIndex = savedInstanceState.getInt("weatherIndex")
        updateWeather(currentWeatherIndex)
    }

    private var currentWeatherIndex: Int = 0

    private fun updateWeather(index: Int? = null) {
        currentWeatherIndex = index ?: Random().nextInt(weatherConditions.size)
        val weatherData = weatherConditions[currentWeatherIndex]

        binding.weatherIcon.setImageResource(weatherData.icon)
        binding.location.text = weatherData.location
        binding.description.text = weatherData.condition
        binding.temperature.text = weatherData.temperature
        binding.humidity.text = "Srážky: ${weatherData.humidity}"
        binding.wind.text = "Vítr: ${weatherData.wind} m/s"

    }
}
