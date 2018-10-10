package com.shanan.schedulist.ui.splash

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.shanan.schedulist.BuildConfig
import com.shanan.schedulist.R
import com.shanan.schedulist.base.BaseViewModel
import com.shanan.schedulist.model.Airports
import com.shanan.schedulist.networking.LufthansaApi
import com.shanan.schedulist.utils.Constants
import com.shanan.schedulist.utils.Constants.BEARER
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SplashViewModel : BaseViewModel() {

    @Inject
    lateinit var api: LufthansaApi

    private lateinit var subscription: Disposable

    var accessToken: String? = null

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener {
        accessToken?.let {
            loadAirports()
        } ?: run {
            onAuthError()
        }
    }

    init {
        requestAccessToken()
    }

    private fun requestAccessToken() {
        subscription = api.authenticate(BuildConfig.LH_CLIENT_ID,
                BuildConfig.LH_CLIENT_SECRET,
                Constants.LH_GRANT_TYPE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRequestStart() }
                .doOnTerminate { onRequestFinish() }
                .subscribe(
                        { onAuthSuccess(it.access_token) },
                        { onAuthError() }
                )
    }

    private fun loadAirports() {

        subscription = api.getAllAirports(BEARER.plus(accessToken))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRequestStart() }
                .doOnTerminate { onRequestFinish() }
                .subscribe({ onRetrieveCitiesSuccess(it.airportResource.airports) },
                        { onRetrieveCitiesError(it.message) }
                )
    }

    private fun onRequestStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRequestFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onAuthSuccess(token: String) {
        accessToken = token
        loadAirports()
    }

    private fun onAuthError() {
        errorMessage.value = R.string.error
    }

    private fun onRetrieveCitiesSuccess(airports: Airports) {
        println("onRetrieveCitiesSuccess")
    }

    private fun onRetrieveCitiesError(message: String?) {
        errorMessage.value = R.string.error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}