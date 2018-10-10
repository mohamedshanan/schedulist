package com.shanan.schedulist.injection.component

import com.shanan.schedulist.injection.module.ContextModule
import com.shanan.schedulist.injection.module.NetworkModule
import com.shanan.schedulist.injection.module.OkHttpClientModule
import com.shanan.schedulist.injection.module.SharedPreferencesModule
import com.shanan.schedulist.ui.splash.SplashViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, OkHttpClientModule::class, NetworkModule::class, SharedPreferencesModule::class])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified SplashViewModel.
     * @param viewModel ViewModel in which to inject the dependencies
     */
    fun inject(viewModel: SplashViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun contextModule(contextModule: ContextModule): Builder
        fun okHttpModule(okHttpClientModule: OkHttpClientModule): Builder
        fun sharedPreferencesModule(sharedPreferencesModule: SharedPreferencesModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder
    }
}