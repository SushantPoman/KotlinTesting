package com.example.kotlintesting.global

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WebserviceUtil private constructor() {
//    https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json
    private val baseUrl = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"
    val retrofit: Retrofit

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }

    companion object {
        private var webServiceUtil: WebserviceUtil? = null

        val instance: WebserviceUtil
            get() {
                if (webServiceUtil == null) {
                    synchronized(WebserviceUtil::class.java) {
                        if (webServiceUtil == null) {
                            webServiceUtil =
                                    WebserviceUtil()
                        }
                    }
                }
                return webServiceUtil!!
            }
    }

}