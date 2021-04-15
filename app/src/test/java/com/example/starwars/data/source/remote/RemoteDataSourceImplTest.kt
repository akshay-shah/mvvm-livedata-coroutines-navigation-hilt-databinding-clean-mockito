package com.example.starwars.data.source.remote

import com.example.starwars.data.source.remote.service.ApiService
import com.example.starwars.mockFilmResponse
import com.example.starwars.mockPlanetResponse
import com.example.starwars.mockSearchResponse
import com.example.starwars.mockSpecieResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class RemoteDataSourceImplTest {
    @Mock
    private lateinit var service: ApiService

    private lateinit var remoteDataSourceImpl: RemoteDataSourceImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        remoteDataSourceImpl = RemoteDataSourceImpl(service)
    }

    @Test
    fun `search character should return response from ApiService`() {
        runBlockingTest {
            whenever(service.searchCharacter("xyz")).thenReturn(mockSearchResponse)
            val response = remoteDataSourceImpl.searchCharacter("xyz")
            verify(service).searchCharacter("xyz")
            assertEquals(mockSearchResponse, response)
        }
    }

    @Test
    fun `getPlanetDetails should return response from ApiService`() {
        runBlockingTest {
            whenever(service.getPlanetDetails("xyz")).thenReturn(mockPlanetResponse)
            val response = remoteDataSourceImpl.getPlanetDetails("xyz")
            verify(service).getPlanetDetails("xyz")
            assertEquals(mockPlanetResponse, response)
        }
    }

    @Test
    fun `getSpecieDetails should return response from ApiService`() {
        runBlockingTest {
            whenever(service.getSpecieDetails("xyz")).thenReturn(mockSpecieResponse)
            val response = remoteDataSourceImpl.getSpecieDetails("xyz")
            verify(service).getSpecieDetails("xyz")
            assertEquals(mockSpecieResponse, response)
        }
    }

    @Test
    fun `getFilmDetails should return response from ApiService`() {
        runBlockingTest {
            whenever(service.getFilmDetails("xyz")).thenReturn(mockFilmResponse)
            val response = remoteDataSourceImpl.getFilmDetails("xyz")
            verify(service).getFilmDetails("xyz")
            assertEquals(mockFilmResponse, response)
        }
    }

    @Test
    fun `search character for invalid data should return null`() {
        runBlockingTest {
            whenever(service.searchCharacter("xyz")).thenReturn(null)
            val response = remoteDataSourceImpl.searchCharacter("xyz")
            verify(service).searchCharacter("xyz")
            assertEquals(null, response)
        }
    }

    @Test
    fun `getPlanetDetails for invalid data should return null`() {
        runBlockingTest {
            whenever(service.getPlanetDetails("xyz")).thenReturn(null)
            val response = remoteDataSourceImpl.getPlanetDetails("xyz")
            verify(service).getPlanetDetails("xyz")
            assertEquals(null, response)
        }
    }

    @Test
    fun `getSpecieDetails for invalid data should return null`() {
        runBlockingTest {
            whenever(service.getSpecieDetails("xyz")).thenReturn(null)
            val response = remoteDataSourceImpl.getSpecieDetails("xyz")
            verify(service).getSpecieDetails("xyz")
            assertEquals(null, response)
        }
    }

    @Test
    fun `getFilmDetails for invalid data should return null`() {
        runBlockingTest {
            whenever(service.getFilmDetails("xyz")).thenReturn(null)
            val response = remoteDataSourceImpl.getFilmDetails("xyz")
            verify(service).getFilmDetails("xyz")
            assertEquals(null, response)
        }
    }
}