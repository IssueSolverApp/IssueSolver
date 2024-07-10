package com.issuesolver.data.network.jwt

import android.content.SharedPreferences
import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.domain.entity.networkModel.login.LoginResponse
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

//class AuthInterceptor(private val sharedPreferences: SharedPreferences): Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        var request = chain.request()
//        val token = sharedPreferences.getString("accessToken", null)
//
//        if (token != null) {
//            request = request.newBuilder()
//                .header("Authorization", "Bearer $token")
//                .build()
//        }
//        return chain.proceed(request)    }
//}

class AuthInterceptor @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPreferences.getString("access_token", null)
        val request = chain.request().newBuilder()
        if (token != null) {
            request.addHeader("Authorization", "Bearer $token")
        }
        return chain.proceed(request.build())
    }
}



class AuthAuthenticator @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = runBlocking {
            sharedPreferences.getString("refresh_token", null)
        }
        return runBlocking {
            val newTokenResponse = getNewToken(refreshToken)
            if (!newTokenResponse.isSuccessful || newTokenResponse.body() == null) {
                // Couldn't refresh the token, so restart the login process
                sharedPreferences.edit().clear().apply()
                null
            } else {
                val newAccessToken = newTokenResponse.body()?.data?.accessToken
                newAccessToken?.let {
                    sharedPreferences.edit().putString("access_token", it).apply()
                    response.request.newBuilder()
                        .header("Authorization", "Bearer $it")
                        .build()
                }
            }
        }
    }

    private suspend fun getNewToken(refreshToken: String?): retrofit2.Response<LoginResponse> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://govermentauthapi20240610022027.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        val service = retrofit.create(LoginService::class.java)
        return service.refreshToken("Bearer $refreshToken")
    }
}


//@Singleton
//class DGAuthenticator(
//    private val context: Context,
//    private val preferences: DGEncryptedSharedPreferences
//) : Authenticator {
//
//    val mutex = Mutex()
//
//    override fun authenticate(route: Route?, response: Response): Request? {
//
//
//        return synchronized(this) {
//
//            runBlocking {
//                refreshToken()?.let {
//                    mutex.withLock {
//                        if (it.isSuccessful) {
//
//                            val loginAndRefreshResponse = it.body()
//
//                            preferences.accessToken =
//                                loginAndRefreshResponse?.accessToken
//                                    ?: preferences.accessToken
//                            preferences.refreshToken =
//                                loginAndRefreshResponse?.refreshToken
//                                    ?: preferences.refreshToken
//
//
//                            return@runBlocking newRequest(
//                                response.request,
//                                preferences.accessToken
//                            )
//
//                        } else if (it.code() == 401) {
//
//                            preferences.clear()
//                            val intent = Intent(context,
//                                LauncherActivity::class.java)
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                            context.startActivity(intent)
//
//                            return@runBlocking null
//
//                        } else {
//                            return@runBlocking null
//                        }
//                    }
//                }
//
//
//                return@runBlocking null
//            }
//
//        }
//
//    }
//
//    private suspend fun refreshToken():
//            retrofit2.Response<LoginAndRefreshResponse>? {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.level = if (BuildConfig.DEBUG) {
//            HttpLoggingInterceptor.Level.BODY
//        } else {
//            HttpLoggingInterceptor.Level.NONE
//        }
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .addInterceptor { chain ->
//                val requestBuilder = chain.request().newBuilder()
//                    .addHeader("Content-Type", "application/json;charset=utf-8")
//                    .addHeader("X-Platform", "Mobile")
//                    .addHeader("X-Client-Type", "Android")
//                    .addHeader("X-AppVersion", BuildConfig.VERSION_NAME)
//                    .addHeader("Accept-Language", "az")
//                val request = requestBuilder.build()
//
//                chain.proceed(request)
//            }
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BuildConfig.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .build()
//        val service = retrofit.create(DGAuthorizationService::class.java)
//
//        val body = JsonObject()
//        body.addProperty("refresh_token", preferences.refreshToken)
//        body.addProperty("certificate", preferences.certificate)
//
//        val bearerToken = preferences.accessToken
//
//
//        if (preferences.refreshToken.isEmpty()) {
//            return null
//        }
//        return service.refreshAccessToken(bearerToken, body)
//    }
//
//    private fun newRequest(request: Request, accessToken: String): Request {
//        return request.newBuilder()
//            .header("Authorization", accessToken)
//            .build()
//    }
//}