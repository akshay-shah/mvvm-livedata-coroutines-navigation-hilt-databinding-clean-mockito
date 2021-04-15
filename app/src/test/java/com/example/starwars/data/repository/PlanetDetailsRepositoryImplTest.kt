package com.example.starwars.data.repository

import com.example.starwars.data.mapper.PlanetDetailsResponseMapper
import com.example.starwars.data.source.DataSource
import com.example.starwars.mockPlanetDetailsResponse
import com.example.starwars.mockPlanetResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class PlanetDetailsRepositoryImplTest {
    @Mock
    private lateinit var mapper: PlanetDetailsResponseMapper

    @Mock
    private lateinit var remoteDataSource: DataSource.RemoteDataSource

    private lateinit var repository: PlanetDetailsRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = PlanetDetailsRepositoryImpl(mapper, remoteDataSource)
    }

    @Test
    fun `getFilmDetails should return a valid response`() {
        runBlockingTest {
            whenever(mapper.toModel(mockPlanetResponse)).thenReturn(mockPlanetDetailsResponse)
            whenever(remoteDataSource.getPlanetDetails("xyz")).thenReturn(mockPlanetResponse)
            val response = repository.getPlanetDetails("xyz")
            verify(remoteDataSource).getPlanetDetails("xyz")
            Assert.assertEquals(
                mockPlanetDetailsResponse.planetDetailsModel,
                response.planetDetailsModel
            )
            Assert.assertEquals(mockPlanetDetailsResponse.error, response.error)
        }
    }
}