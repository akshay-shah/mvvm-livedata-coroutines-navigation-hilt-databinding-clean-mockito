package com.example.starwars.domain.usecase

import com.example.starwars.domain.repository.PlanetRepository
import com.example.starwars.mockPlanetDetailsResponse
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
class GetPlanetDetailsUseCaseTest {
    @Mock
    private lateinit var repository: PlanetRepository

    private lateinit var useCase: GetPlanetDetailsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetPlanetDetailsUseCase(repository)
    }

    @Test
    fun `executeUseCase should call repository`() {
        runBlockingTest {
            whenever(repository.getPlanetDetails("2")).thenReturn(mockPlanetDetailsResponse)
            val response =
                useCase.executeUseCase(GetPlanetDetailsUseCase.GetPlanetDetailsRequest("2"))
            verify(repository).getPlanetDetails("2")
            Assert.assertEquals(mockPlanetDetailsResponse, response)
        }
    }
}