package com.example.starwars.data.repository

import com.example.starwars.data.mapper.SpecieDetailsResponseMapper
import com.example.starwars.data.model.SpecieResponse
import com.example.starwars.data.source.DataSource
import com.example.starwars.mockSearchCharacterResponse
import com.example.starwars.mockSpecieDetailsResponse
import com.example.starwars.mockSpecieResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class SpecieDetailsRepositoryImplTest {
    @Mock
    private lateinit var mapper: SpecieDetailsResponseMapper

    @Mock
    private lateinit var remoteDataSource: DataSource.RemoteDataSource

    private lateinit var repository: SpecieDetailsRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = SpecieDetailsRepositoryImpl(mapper, remoteDataSource)
    }

    @Test
    fun `getFilmDetails should return a valid response`() {
        runBlockingTest {
            val argumentCaptor = argumentCaptor<SpecieResponse>()
            whenever(mapper.toModel(mockSpecieResponse)).thenReturn(mockSpecieDetailsResponse)
            whenever(remoteDataSource.getSpecieDetails("xyz")).thenReturn(mockSpecieResponse)
            val response = repository.getSpecieDetails("xyz")
            verify(remoteDataSource).getSpecieDetails("xyz")
            assertEquals(
                mockSpecieDetailsResponse.specieDetailsModel,
                response.specieDetailsModel
            )
            assertEquals(mockSearchCharacterResponse.error, response.error)
        }
    }
}