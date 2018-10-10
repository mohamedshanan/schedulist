package com.shanan.schedulist.base

import androidx.lifecycle.ViewModel
import com.shanan.schedulist.injection.component.DaggerViewModelInjector
import com.shanan.schedulist.injection.component.ViewModelInjector
import com.shanan.schedulist.injection.module.NetworkModule
import com.shanan.schedulist.ui.splash.SplashViewModel

open class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule())
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is SplashViewModel -> injector.inject(this)
        }
    }
}