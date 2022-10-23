package com.example.movieapps.data.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.movieapps.data.worker.BlurWorker
import com.example.movieapps.data.worker.KEY_IMAGE_URI

class BlurViewModel(application: Application): ViewModel() {
    //  var untuk instance  WorkManager di ViewModel
    private val workManager = WorkManager.getInstance(application)
    private var imageUri: Uri? = null


    //    make WorkRequest & beritahu WM untuk jalankan
    internal fun applyBlur(blurLevel: Int) {
        val blurRequest = OneTimeWorkRequestBuilder<BlurWorker>()
            .setInputData(createInputDataForUri())
            .build()
        workManager.enqueue(blurRequest)
    }

    //create URI img
    private fun createInputDataForUri(): Data {
        val builder = Data.Builder()
        imageUri?.let {
            builder.putString(KEY_IMAGE_URI, imageUri.toString())
        }
        return builder.build()
    }

    //    get image URI
    internal fun getImageUri(uri: Uri) {
        imageUri = uri
    }
}

class BlurViewModelFactory(private val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BlurViewModel::class.java)) {
            BlurViewModel(application) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}