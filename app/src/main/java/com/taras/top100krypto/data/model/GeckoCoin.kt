package com.taras.top100krypto.data.model

data class GeckoCoin(
    var id: String,
    var symbol: String,
    var name: String,
    var image: String,
    var current_price: Float,
    var market_cap: Float,
    var market_cap_rank: Int,
    var total_volume: Float,
    var price_change_percentage_24h: Float,
    var market_cap_change_percentage_24h: Float,
    var circulating_supply: Double,
    var total_supply: Long,
    var ath: Float,
    var ath_change_percentage: Float
)

data class GeckoCoinChart(
    var prices: List<List<Float>>
)