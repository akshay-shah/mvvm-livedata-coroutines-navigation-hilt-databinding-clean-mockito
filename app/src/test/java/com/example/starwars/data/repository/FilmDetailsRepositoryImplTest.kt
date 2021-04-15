package com.example.starwars.data.repository

import com.example.starwars.data.mapper.FilmDetailsResponseMapper
import com.example.starwars.data.source.DataSource
import com.example.starwars.mockFilmDetailsResponse
import com.example.starwars.mockFilmResponse
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
class FilmDetailsRepositoryImplTest {

    @Mock
    private lateinit var mapper: FilmDetailsResponseMapper

    @Mock
    private lateinit var remoteDataSource: DataSource.RemoteDataSource

    private lateinit var repository: FilmDetailsRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = FilmDetailsRepositoryImpl(mapper, remoteDataSource)
    }

    @Test
    fun `getFilmDetails should return a valid response`() {
        runBlockingTest {
            whenever(mapper.toModel(mockFilmResponse)).thenReturn(mockFilmDetailsResponse)
            whenever(remoteDataSource.getFilmDetails("xyz")).thenReturn(mockFilmResponse)
            val response = repository.getFilmDetails("xyz")
            verify(remoteDataSource).getFilmDetails("xyz")
            assertEquals(mockFilmDetailsResponse.filmDetailsModel, response.filmDetailsModel)
            assertEquals(mockFilmDetailsResponse.error, response.error)
        }
    }
}