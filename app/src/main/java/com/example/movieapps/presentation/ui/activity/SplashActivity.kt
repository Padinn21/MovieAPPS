package com.example.movieapps.presentation.ui.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import com.example.movieapps.R
import com.example.movieapps.data.viewmodel.LoginViewModel
import com.example.movieapps.data.viewmodel.ViewModelFactory

class SplashActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var pref: com.example.movieapps.data.datastore.LoginDataStoreManager
    private lateinit var viewModelLoginPref: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sharedPreferences = this.getSharedPreferences(
            "datauser",
            Context.MODE_PRIVATE
        )



        pref = com.example.movieapps.data.datastore.LoginDataStoreManager(this)
        viewModelLoginPref = ViewModelProvider(this, ViewModelFactory(pref))[LoginViewModel::class.java]
        supportActionBar?.hide()

        Handler().postDelayed({
            viewModelLoginPref.getUser().observe(this, {
                if (it.username == "" && it.password == "") {
                    startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            })
        }, 3000)
    }
}