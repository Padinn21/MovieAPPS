package com.example.movieapps.data.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movieapps.data.model.FavoriteEntity

@Dao
interface FavoriteMovieDao {
    @Query("SELECT * FROM FavoriteEntity ORDER BY id DESC")
    fun getDataFavorite() : LiveData<List<FavoriteEntity>>

    @Insert
    fun insertFavorite(movie: FavoriteEntity)

    @Delete
    fun deleteFavorite(movie: FavoriteEntity)

    @Query("SELECT * FROM FavoriteEntity WHERE id = :id")
    fun checkMovie(id: Int) : Int
}