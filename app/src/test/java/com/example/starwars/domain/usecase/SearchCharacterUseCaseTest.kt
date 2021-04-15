package com.example.starwars.domain.usecase

import com.example.starwars.domain.repository.SearchCharacterRepository
import com.example.starwars.mockSearchCharacterResponse
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
class SearchCharacterUseCaseTest {
    @Mock
    private lateinit var repository: SearchCharacterRepository

    private lateinit var useCase: SearchCharacterUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = SearchCharacterUseCase(repository)
    }

    @Test
    fun `executeUseCase should call repository`() {
        runBlockingTest {
            whenever(repository.searchCharacter("xyz")).thenReturn(mockSearchCharacterResponse)
            val response =
                useCase.executeUseCase(SearchCharacterUseCase.SearchCharacterRequest("xyz"))
            verify(repository).searchCharacter("xyz")
            Assert.assertEquals(mockSearchCharacterResponse, response)
        }
    }
}