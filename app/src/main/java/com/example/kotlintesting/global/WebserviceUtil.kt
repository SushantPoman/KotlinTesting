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

        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        retrofit = builder.client(httpClient).build()
    }

    companion object {
        private var self: WebserviceUtil? = null

        val instance: WebserviceUtil
            get() {
                if (self == null) {
                    synchronized(WebserviceUtil::class.java) {
                        if (self == null) {
                            self =
                                    WebserviceUtil()
                        }
                    }
                }
                return self!!
            }
    }

}