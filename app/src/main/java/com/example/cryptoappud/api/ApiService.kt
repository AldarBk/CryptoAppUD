package com.example.cryptoappud.api

import com.example.cryptoappud.data.CoinInfoListOfData
import com.example.cryptoappud.data.CoinPriceInfoRawDate
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "7ef4f283d47496d5e085c3a455033b3c4632f21f0292697eb766fcf45348438e",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ): Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "7ef4f283d47496d5e085c3a455033b3c4632f21f0292697eb766fcf45348438e",
        @Query(QUERY_PARAM_FROM_TO_SYMBOL) fSyms: String?,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY
    ): Single<CoinPriceInfoRawDate>

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_TO_SYMBOL = "ftsyms"

        private const val CURRENCY = "USD"
    }
}