package com.shanan.schedulist.injection.module

import android.content.Context
import android.content.SharedPreferences
import com.shanan.schedulist.utils.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(ContextModule::class))
class SharedPreferencesModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.SHARED_PREFERENCES_SETTINGS, Context.MODE_PRIVATE)
    }
}
