package com.example.starwars.domain.usecase

import com.example.starwars.domain.repository.FilmRepository
import com.example.starwars.mockFilmDetailsResponse
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
class GetFilmDetailsUseCaseTest {
    @Mock
    private lateinit var repository: FilmRepository

    private lateinit var useCase: GetFilmDetailsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetFilmDetailsUseCase(repository)
    }

    @Test
    fun `executeUseCase should call repository`() {
        runBlockingTest {
            whenever(repository.getFilmDetails("2")).thenReturn(mockFilmDetailsResponse)
            val response =
                useCase.executeUseCase(GetFilmDetailsUseCase.GetFilmDetailsRequest("2"))
            verify(repository).getFilmDetails("2")
            Assert.assertEquals(mockFilmDetailsResponse, response)
        }
    }
}