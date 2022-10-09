package com.example.movieapps.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.movieapps.R
import com.example.movieapps.data.viewmodel.ViewModelUser
import com.example.movieapps.databinding.ActivityLoginBinding
import java.util.*

class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        sharedPreferences = this.getSharedPreferences("datauser",
            Context.MODE_PRIVATE)

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        binding.btnIndo.setOnClickListener {
            setLocale("in")
        }

        binding.btnUsa.setOnClickListener {
            setLocale("en")
        }

        binding.btnLogin.setOnClickListener {

            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val saveUser = sharedPreferences.edit()
            val viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
            viewModel.callGetUser()
            viewModel.getLiveDataUser().observe(this, {
                if (it != null) {
                    for (i in it) {
                        if (i.username == username && i.password == password) {
                            saveUser.putString("id", i.id)
                            saveUser.putString("name", i.name)
                            saveUser.putString("username", i.username)
                            saveUser.putString("password", i.password)
                            saveUser.putString("address", i.address)
                            saveUser.putString("age", i.age)
                            saveUser.apply()
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        }
                    }
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Gagal Login",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        }
    }

    private fun setLocale(lang: String) {
        val myLocale = Locale(lang)
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
        startActivity(Intent(this, LoginActivity::class.java))
    }
}