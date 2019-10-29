package com.bonelesschicken.beholder.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    private val baseUrl = "http://35.193.19.247/"

    private val gson = GsonBuilder()
        .create()

    private val builder = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(
            OkHttpClient().newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build())
        .addConverterFactory(GsonConverterFactory.create(gson))

    private var retrofit = builder.build()

    /**
     * Creates a service using the retrofit builder.
     * @param serviceClass Class
     * @param <S> S
     * @return Returns the service
    </S> */
    private fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

    var service: SpellbinderService = createService(SpellbinderService::class.java)
}