package com.issuesolver.data.network.di

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.data.network.jwt.AuthAuthenticator
import com.issuesolver.data.network.jwt.AuthInterceptor
import com.issuesolver.data.network.newrequest.NewRequestService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
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
    fun provideApiClient(gson: Gson, client: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder()
        retrofit.baseUrl("https://gatewayy-f20db7ab0323.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

        return retrofit.build()

    }

    @Provides
    @Singleton
    fun provideApiService( retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    @Provides
    @Singleton
    fun provideNewRequestService( retrofit: Retrofit): NewRequestService {
        return retrofit.create(NewRequestService::class.java)
    }

    private val loggingInterceptor =
        HttpLoggingInterceptor { message -> Log.d("OkHttp", message) }.apply {
            level =
                HttpLoggingInterceptor.Level.BODY // Уровень логирования: BODY, HEADERS или BASIC
        }

    @Provides
    @Singleton
    fun provideOkhttpClient(authInterceptor: AuthInterceptor,
                            authAuthenticator: AuthAuthenticator,): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .authenticator(authAuthenticator)

        return client.build()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }


    @Provides
    @Singleton
    fun provideAuthInterceptor(sharedPreferences: SharedPreferences): AuthInterceptor {
        return AuthInterceptor(sharedPreferences)
    }


    @Provides
    @Singleton
    fun provideAuthAuthenticator(sharedPreferences: SharedPreferences): AuthAuthenticator {
        return AuthAuthenticator(sharedPreferences)
    }














}