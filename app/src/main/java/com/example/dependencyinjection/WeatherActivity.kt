package com.example.dependencyinjection

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.dependencyinjection.viewstate.WeatherViewState
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

// To be able to inject activities and fragment, you need to create a @ContributesAndroidInjector
// method for each activity/fragment in a Dagger module (see WeatherModule).
class WeatherActivity : AppCompatActivity() {

    // Ask Dagger to set this field for us.
    @Inject
    lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // Performs the actual setting of the fields. Without this, the fields will be null.
        AndroidInjection.inject(this) //must be called before super.onCreate():
        //The above line is not required in new Android Dagger. Instead of above line, we can create
        //an abstract ActivityBindingModule @Module class and list down the activities with
        //annotation @ContributesAndroidInjector
        //Example:
        //      @Module
        //      abstract class WeatherModule {
        //          @ContributesAndroidInjector
        //          abstract fun contributeWeatherActivity() : WeatherActivity
        //      }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonGetWeatherData.setOnClickListener {
            weatherViewModel.getWeatherDetails().observe(this, Observer {
                when (it) {
                    is WeatherViewState.Loading -> {
                        textViewResponse.text = "Getting Data"
                        Toast.makeText(this, "Getting Data", Toast.LENGTH_LONG).show()
                    }
                    is WeatherViewState.Data -> {
                        textViewResponse.text = it.data.toString()
                    }
                    is WeatherViewState.Error -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }.exhaustive
            })
        }
    }

    val <T> T.exhaustive: T
        get() = this
}
