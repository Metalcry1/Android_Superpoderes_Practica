package com.example.android_superpoderes_practica.di


import android.util.Log
import com.example.android_superpoderes_practica.AppClass.Companion.prefRepository
import com.example.android_superpoderes_practica.dataa.Remote.MarvelApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.Credentials
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val BASE_URL = "https://gateway.marvel.com/v1/public/"
    private val apikey = "f3603d6d0330a129071bf9dfa05b49ca"
    private val hash = "0864ea25976921785806d9e08ba4c893"
    private val timestamp = "1"

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val originalRequest = chain.request()

            val urlWithParams = originalRequest.url.newBuilder()
                .addQueryParameter("apikey", apikey)
                .addQueryParameter("hash", hash)
                .addQueryParameter("ts", timestamp)
                .build()

            val newRequest = originalRequest.newBuilder()
                .url(urlWithParams)
                .build()
            Log.w("PROVIDES", newRequest.toString())
            chain.proceed(newRequest)


        }.build()
    }

    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder().client(okHttpClient).baseUrl("https://gateway.marvel.com/v1/public/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    }

    @Provides
    fun providesDragonBallApi(retrofit: Retrofit): MarvelApi {
        return retrofit.create(MarvelApi::class.java)
    }


}
