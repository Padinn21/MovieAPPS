package com.example.movieapps.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.movieapps.data.model.FavoriteEntity
import com.example.movieapps.data.room.FavoriteDatabase
import com.example.movieapps.data.room.FavoriteMovieDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private var favoriteDao: FavoriteMovieDao?
    private var favoriteDatabase: FavoriteDatabase?

    init {
        favoriteDatabase = FavoriteDatabase.getDatabase(application)
        favoriteDao = favoriteDatabase?.favoriteMovieDao()
    }

    fun getFavoriteMovie(): LiveData<List<FavoriteEntity>>? {
        return favoriteDao?.getDataFavorite()
    }

    fun addToFavorite(id: Int,
                      originalTitle: String,
                      posterPath: String,
                      voteAverage: Double,
                      overview: String ) {
        CoroutineScope(Dispatchers.IO).launch {
            var movie = FavoriteEntity(id, originalTitle, posterPath, voteAverage, overview)
            favoriteDao?.insertFavorite(movie)
        }
    }

    suspend fun checkUser(id: Int) = favoriteDao?.checkMovie(id)

    fun removeFromFavorite(id: Int,
                           originalTitle: String,
                           posterPath: String,
                           voteAverage: Double,
                           overview: String) {
        CoroutineScope(Dispatchers.IO).launch {
            var movie = FavoriteEntity(id, originalTitle, posterPath, voteAverage, overview)
            favoriteDao?.deleteFavorite(movie)
        }
    }


}