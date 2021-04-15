package com.example.starwars.data.repository

import com.example.starwars.data.mapper.SearchCharacterResponseMapper
import com.example.starwars.data.source.DataSource
import com.example.starwars.mockSearchCharacterResponse
import com.example.starwars.mockSearchResponse
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
class SearchCharacterRepositoryImplTest {
    @Mock
    private lateinit var mapper: SearchCharacterResponseMapper

    @Mock
    private lateinit var remoteDataSource: DataSource.RemoteDataSource

    private lateinit var repository: SearchCharacterRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = SearchCharacterRepositoryImpl(mapper, remoteDataSource)
    }

    @Test
    fun `getFilmDetails should return a valid response`() {
        runBlockingTest {
            whenever(mapper.toModel(mockSearchResponse)).thenReturn(mockSearchCharacterResponse)
            whenever(remoteDataSource.searchCharacter("xyz")).thenReturn(mockSearchResponse)
            val response = repository.searchCharacter("xyz")
            verify(remoteDataSource).searchCharacter("xyz")
            Assert.assertEquals(
                mockSearchCharacterResponse.characterModel,
                response.characterModel
            )
            Assert.assertEquals(mockSearchCharacterResponse.error, response.error)
        }
    }
}