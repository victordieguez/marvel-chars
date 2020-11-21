package com.android.marvelcharacters.network

import android.content.Context
import com.android.marvelcharacters.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {
    fun <T> buildService(service: Class<T>, context: Context): T {
        val client = OkHttpClient.Builder().addInterceptor(APIInterceptor(context.getString(R.string.public_api_key), context.getString(R.string.private_api_key))).build()
        val retrofit = Retrofit.Builder().baseUrl(NetworkURL.BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build()
        return retrofit.create(service)
    }
}