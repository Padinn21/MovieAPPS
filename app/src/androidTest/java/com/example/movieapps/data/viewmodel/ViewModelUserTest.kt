package com.example.movieapps.data.viewmodel

import com.example.movieapps.data.model.DataUser
import com.example.movieapps.data.model.ResponseUserItem
import com.example.movieapps.data.network.RestfulUser
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Call

class ViewModelUserTest {
    lateinit var service: RestfulUser

    @Before
    fun setUp() {
        service = mockk()
    }

    @Test
    fun getAllUser(): Unit = runBlocking {

        val responGetAllUser = mockk<Call<List<ResponseUserItem>>>()

        every {
            runBlocking {
                service.getAllUser()
            }
        } returns responGetAllUser

        val result = service.getAllUser()

        verify {
            runBlocking {
                service.getAllUser()
            }
        }
        Assert.assertEquals(result, responGetAllUser)
    }

    @Test
    fun postUser(): Unit = runBlocking {

        val responPostUser = mockk<Call<ResponseUserItem>>()

        every {
            runBlocking {
                service.postUser(DataUser("Alif Izzuddin", "alf", "123", "25", "Bandung"))
            }
        } returns responPostUser

        val result = service.postUser(DataUser("Alif Izzuddin", "alf", "123", "25", "Bandung"))

        verify {
            runBlocking {
                service.postUser(DataUser("Alif Izzuddin", "alf", "123", "25", "Bandung"))
            }
        }
        Assert.assertEquals(result, responPostUser)
    }

    @Test
    fun updateUser(): Unit = runBlocking {

        val responUpdateUser = mockk<Call<ResponseUserItem>>()

        every {
            runBlocking {
                service.updateUser("10", DataUser(" Alif Izzuddin", "alf", "123", "25", "Bandung"))
            }
        } returns responUpdateUser

        val result =
            service.updateUser("10", DataUser("Alif Izzuddin", "alf", "123", "25", "Bandung"))

        verify {
            runBlocking {
                service.updateUser("10", DataUser("Alif Izzuddin", "alf", "123", "25", "Bandung"))
            }
        }
        Assert.assertEquals(result, responUpdateUser)
    }

    @Test
    fun getUserById(): Unit = runBlocking {

        val responGetUserById = mockk<Call<ResponseUserItem>>()

        every {
            runBlocking {
                service.getUserById("10")
            }
        } returns responGetUserById

        val result = service.getUserById("10")

        verify {
            runBlocking {
                service.getUserById("10")
            }
        }
        Assert.assertEquals(result, responGetUserById)
    }
}
