package com.example.cryptoappud

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.cryptoappud.api.ApiFactory
import com.example.cryptoappud.database.AppDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()
    val priceLIst = db.coinPriceInfoDao().getPriceList()

    fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            Log.d("TEST_OF_LOADING_DATA", "Success: $it")
        }, {
            Log.d("TEST_OF_LOADING_DATA", "Failure: ${it.message}")
        })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}