package com.issuesolver.presentation.splash

import BottomBarScreen
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.presentation.navigation.AuthScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val sharedPreferences: SharedPreferences) :ViewModel(){
    private val _navigateTo = MutableLiveData<String>()
    val navigateTo: LiveData<String> get() = _navigateTo
    fun getToken(): String?{
        val token = sharedPreferences.getString("access_token", null)
        return token
    }

}