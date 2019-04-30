package com.taras.top100krypto.data.service

import com.taras.top100krypto.data.model.GeckoCoin
import com.taras.top100krypto.data.model.GeckoCoinChart
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoService {
    @GET("coins/markets")
    fun getCoinMarket(
            @Query("vs_currency") vsCurrency: String = "usd",
            @Query("per_page") perPage: Int = 100,
            @Query("sparkline") sparkLine: Boolean = false,
            @Query("order") order: String = "market_cap_desc"
    ): Observable<List<GeckoCoin>>

    @GET("coins/{id}/market_chart")
    fun getCoinMarketChart(
            @Path("id") id: String,
            @Query("vs_currency") vsCurrency: String = "usd",
            @Query("days") days: String = "max"
    ): Observable<GeckoCoinChart>
}