package com.example.starwars.presentation.ui.detail

import com.example.starwars.*
import com.example.starwars.domain.usecase.GetFilmDetailsUseCase
import com.example.starwars.domain.usecase.GetPlanetDetailsUseCase
import com.example.starwars.domain.usecase.GetSpecieDetailsUseCase
import com.example.starwars.presentation.mapper.CharacterDetailMapper
import com.example.starwars.presentation.ui.BaseViewModelTest
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class DetailsViewModelTest : BaseViewModelTest() {
    @Mock
    private lateinit var getSpecieDetailsUseCase: GetSpecieDetailsUseCase

    @Mock
    private lateinit var getFilmDetailsUseCase: GetFilmDetailsUseCase

    @Mock
    private lateinit var getPlanetDetailsUseCase: GetPlanetDetailsUseCase

    @Mock
    private lateinit var mapper: CharacterDetailMapper

    private lateinit var viewModel: DetailsViewModel

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        viewModel = DetailsViewModel(
            testScope,
            getSpecieDetailsUseCase,
            getFilmDetailsUseCase,
            getPlanetDetailsUseCase,
            mapper
        )
    }

    @Test
    fun `on viewmodel init should get valid data from network`() {
        testScope.runBlockingTest {
            whenever(getFilmDetailsUseCase.executeUseCase(any())).thenReturn(mockFilmDetailsResponse)
            whenever(getSpecieDetailsUseCase.executeUseCase(any())).thenReturn(
                mockSpecieDetailsResponse
            )
            whenever(getPlanetDetailsUseCase.executeUseCase(any())).thenReturn(
                mockPlanetDetailsResponse
            )
            whenever(mapper.toModel(any(), any(), any(), any(), any(), any())).thenReturn(
                mockCharacterDetailsModel
            )
            val captorSpecieDetail =
                argumentCaptor<GetSpecieDetailsUseCase.GetSpecieDetailsRequest>()
            val captorFilmDetail = argumentCaptor<GetFilmDetailsUseCase.GetFilmDetailsRequest>()
            val captorPlanetDetail =
                argumentCaptor<GetPlanetDetailsUseCase.GetPlanetDetailsRequest>()
            viewModel.init(mockCharacterInfoModel)
            verify(getSpecieDetailsUseCase, times(2)).executeUseCase(captorSpecieDetail.capture())
            verify(getFilmDetailsUseCase, times(2)).executeUseCase(captorFilmDetail.capture())
            verify(getPlanetDetailsUseCase, times(1)).executeUseCase(captorPlanetDetail.capture())
            assertEquals("1", captorSpecieDetail.firstValue.id)
            assertEquals("2", captorSpecieDetail.secondValue.id)
            assertEquals("1", captorFilmDetail.firstValue.id)
            assertEquals("2", captorFilmDetail.secondValue.id)
            assertEquals("homeWorld", captorPlanetDetail.firstValue.id)
            assertEquals(mockCharacterDetailsModel, viewModel.characterDetailsModel.value)

        }
    }
}