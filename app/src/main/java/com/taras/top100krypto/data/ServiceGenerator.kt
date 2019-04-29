package com.taras.top100krypto.data

import com.taras.top100krypto.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    private val httpClient: OkHttpClient.Builder
    private val builder: Retrofit.Builder

    init {
        httpClient = OkHttpClient.Builder().apply {
            addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                chain.proceed(requestBuilder.build())
            }

//            if (BuildConfig.DEBUG) {
//                addNetworkInterceptor(StethoInterceptor())
//            }
        }

        builder = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(createGsonConverterFactory())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
    }

    private fun createGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    fun <S> createService(serviceClass: Class<S>): S {
//        Timber.d("Call <%s>", serviceClass.simpleName)
        val client = httpClient
            .build()
        val retrofit = builder
            .client(client)
            .build()
        return retrofit.create(serviceClass)
    }
}