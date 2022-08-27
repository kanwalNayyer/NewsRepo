package com.hisham.news.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.hisham.news.data.api.ApiKeys.BASE_URL
import com.hisham.news.data.api.NewsApiServices
import com.hisham.news.network.NetworkConnectionInterceptor
import com.hisham.news.network.ResponseInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val apiModule= module {
    single {
        createOkHttpClient(NetworkConnectionInterceptor(get()),ResponseInterceptor(get(),get()))
    }
    single {
        createRetrofit<NewsApiServices>(get())
    }
}

fun createOkHttpClient(networkConnectionInterceptor: NetworkConnectionInterceptor,responseInterceptor: ResponseInterceptor):OkHttpClient{

    val httpLoggingInterceptor=HttpLoggingInterceptor()
    httpLoggingInterceptor.level=HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(240,TimeUnit.SECONDS)
        .readTimeout(240,TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(networkConnectionInterceptor)
        .addInterceptor(responseInterceptor)
        .addNetworkInterceptor { chain ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .build()
            )
        }
        .build()
}

inline fun<reified T>createRetrofit(okHttpClient: OkHttpClient):T{

    val retrofit=Retrofit.Builder()
       .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    return retrofit.create(T::class.java)
}
