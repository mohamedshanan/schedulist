package com.shanan.schedulist.injection.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.shanan.schedulist.networking.LufthansaApi
import com.shanan.schedulist.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = arrayOf(OkHttpClientModule::class))
class NetworkModule {

    @Provides
    @Singleton
    fun provideLufthansaApi(retrofit: Retrofit): LufthansaApi {
        return retrofit.create(LufthansaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient,
                        gsonConverterFactory: GsonConverterFactory, gson: Gson, rxJava2CallAdapterFactory: RxJava2CallAdapterFactory): Retrofit {

        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }
}
