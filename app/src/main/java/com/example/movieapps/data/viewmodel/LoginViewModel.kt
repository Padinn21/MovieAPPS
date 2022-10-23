package com.example.movieapps.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapps.data.datastore.LoginDataStoreManager
import com.example.movieapps.data.model.UserPreferences
import kotlinx.coroutines.launch

class LoginViewModel(private val pref: LoginDataStoreManager): ViewModel() {
    fun getUser(): LiveData<UserPreferences> {
        return pref.getUser().asLiveData()
    }

    fun setUserLogin(isLogin: Boolean) {
        viewModelScope.launch {
            pref.setUserLogin(isLogin)
        }
    }

    fun saveUser(id: String,
                 name: String,
                 username: String,
                 password: String,
                 age: String,
                 address: String) {
        viewModelScope.launch {
            pref.setUser(id,name, username, password, age, address)
        }
    }
}