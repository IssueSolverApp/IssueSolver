package com.issuesolver.data.network.di

import android.util.Log
import com.google.gson.Gson
import com.issuesolver.data.network.auth.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideApiClient(gson: Gson, client: OkHttpClient) : Retrofit {
        val retrofit = Retrofit.Builder()
        retrofit.baseUrl("https://govermentauthapi20240610022027.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

        return retrofit.build()

    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    private val loggingInterceptor = HttpLoggingInterceptor { message -> Log.d("OkHttp", message) }.apply {
        level = HttpLoggingInterceptor.Level.BODY // Уровень логирования: BODY, HEADERS или BASIC
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)

        return client.build()
    }

}