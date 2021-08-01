package com.example.dependencyinjection.di

import com.example.dependencyinjection.app.WeatherApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton //WeatherComponent owns Singleton scope. What it means is WeatherComponent lives as long as the app itself
@Component(modules = [AndroidSupportInjectionModule::class, WeatherModule::class])
interface WeatherComponent : AndroidInjector<WeatherApplication>{
    //what above line means is WeatherApplication is a client and WeatherComponent is a service
}


    //Below code is 2nd way of writing this component class
/*
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, WeatherModule::class])
interface WeatherComponent : AndroidInjector<WeatherApplication>{
    //what above line means is WeatherApplication is a client and WeatherComponent is a service

    @Component.Builder
    interface CompBuilder {
        fun build() : WeatherComponent

        // @BindsInstance replaces Builder appModule(AppModule appModule)
        // And removes Constructor with Application AppModule(Application)
        //@BindsInstance is used to bind some instance/value to component at the time of Component Creation
        //Basically we are binding this Component to lifecycle of the application
        @BindsInstance
        fun bindApplicationInstanceToComponent(application: WeatherApplication) : CompBuilder
    }
}
*/
