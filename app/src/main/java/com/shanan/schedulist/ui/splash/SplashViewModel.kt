package com.shanan.schedulist.ui.splash

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.shanan.schedulist.BuildConfig
import com.shanan.schedulist.R
import com.shanan.schedulist.base.BaseViewModel
import com.shanan.schedulist.networking.LufthansaApi
import com.shanan.schedulist.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SplashViewModel : BaseViewModel() {

    @Inject
    lateinit var api: LufthansaApi

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadCities() }

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
                        { onAuthSuccess() },
                        { onAuthError() }
                )
    }

    private fun loadCities() {
        subscription = api.getCities(20, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRequestStart() }
                .doOnTerminate { onRequestFinish() }
                .subscribe(
                        { onRetrieveCitiesSuccess() },
                        { onRetrieveCitiesError() }
                )
    }

    private fun onRequestStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRequestFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onAuthSuccess() {
        loadCities()
    }

    private fun onAuthError() {
        errorMessage.value = R.string.error
    }

    private fun onRetrieveCitiesSuccess() {

    }

    private fun onRetrieveCitiesError() {
        errorMessage.value = R.string.error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}