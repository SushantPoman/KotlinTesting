package com.example.kotlintesting.global

import android.util.Log

object Logs {

    fun i(tag: String, details: String){
        Log.i(tag, details)
    }

    fun e(tag: String, details: String){
        Log.e(tag, details)
    }


}