package com.example.movieapps.data.viewmodel

import com.example.movieapps.data.model.ResponsePopularMovie
import com.example.movieapps.data.model.SerialResponse
import com.example.movieapps.data.network.ApiService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Call

class ViewModelPopularMovieTest {

    lateinit var service: ApiService

    @Before
    fun setUp() {
        service = mockk()
    }

    @Test
    fun getMoviePopular() : Unit = runBlocking {

        val responMoviePopular = mockk<Call<ResponsePopularMovie>>()

        every {
            runBlocking {
                service.getPopularMovie()
            }
        } returns responMoviePopular

        val result = service.getPopularMovie()

        verify {
            runBlocking {
                service.getPopularMovie()
            }
        }
        Assert.assertEquals(result,responMoviePopular)
    }

    @Test
    fun getTvPopular() : Unit = runBlocking {

        val responTvPopular = mockk<Call<SerialResponse>>()

        every {
            runBlocking {
                service.getTvSerial()
            }
        } returns responTvPopular

        val result = service.getTvSerial()

        verify {
            runBlocking {
                service.getTvSerial()
            }
        }
        Assert.assertEquals(result,responTvPopular)
    }
}