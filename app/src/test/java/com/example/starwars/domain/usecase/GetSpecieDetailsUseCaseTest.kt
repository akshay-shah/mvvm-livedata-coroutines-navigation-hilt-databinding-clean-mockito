package com.example.starwars.domain.usecase

import com.example.starwars.domain.repository.SpecieRepository
import com.example.starwars.mockSpecieDetailsResponse
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
class GetSpecieDetailsUseCaseTest {
    @Mock
    private lateinit var repository: SpecieRepository

    private lateinit var useCase: GetSpecieDetailsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetSpecieDetailsUseCase(repository)
    }

    @Test
    fun `executeUseCase should call repository`() {
        runBlockingTest {
            whenever(repository.getSpecieDetails("2")).thenReturn(mockSpecieDetailsResponse)
            val response =
                useCase.executeUseCase(GetSpecieDetailsUseCase.GetSpecieDetailsRequest("2"))
            verify(repository).getSpecieDetails("2")
            assertEquals(mockSpecieDetailsResponse, response)
        }
    }
}