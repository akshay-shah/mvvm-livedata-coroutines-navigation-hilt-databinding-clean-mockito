package com.example.starwars.presentation.ui.home

import com.example.starwars.domain.usecase.SearchCharacterUseCase
import com.example.starwars.mockCharacterInfoModel
import com.example.starwars.mockCharacterNamesModel
import com.example.starwars.mockSearchCharacterResponse
import com.example.starwars.presentation.mapper.CharacterInfoMapper
import com.example.starwars.presentation.mapper.CharacterNameMapper
import com.example.starwars.presentation.ui.BaseViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class HomeViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var searchCharacterUseCase: SearchCharacterUseCase

    @Mock
    private lateinit var characterInfoMapper: CharacterInfoMapper

    @Mock
    private lateinit var characterNameMapper: CharacterNameMapper


    private lateinit var viewModel: HomeViewModel


    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        viewModel =
            HomeViewModel(
                testScope,
                searchCharacterUseCase,
                characterInfoMapper,
                characterNameMapper
            )
    }

    @Test
    fun `onItemClickListener should post value to characterDetails`() {
        testScope.runBlockingTest {
            whenever(
                searchCharacterUseCase.executeUseCase(
                    any()
                )
            ).thenReturn(
                mockSearchCharacterResponse
            )
            whenever(characterInfoMapper.toModel(any())).thenReturn(mockCharacterInfoModel)
            viewModel.queryTextListener.onQueryTextSubmit("xyz")
            viewModel.onListItemClickListener.invoke(1)
            assertEquals(viewModel.characterDetail.value, mockCharacterInfoModel)
        }
    }

    @Test
    fun `onQueryTextSubmit should call search character use case with valid response`() {
        testScope.runBlockingTest {
            whenever(
                searchCharacterUseCase.executeUseCase(
                    any()
                )
            ).thenReturn(
                mockSearchCharacterResponse
            )
            whenever(characterNameMapper.toModel(any())).thenReturn(mockCharacterNamesModel)
            val argumentCaptor = argumentCaptor<SearchCharacterUseCase.SearchCharacterRequest>()
            viewModel.queryTextListener.onQueryTextSubmit("xyz")
            verify(searchCharacterUseCase).executeUseCase(
                argumentCaptor.capture()
            )
            assertEquals("xyz", argumentCaptor.firstValue.query)
            assertEquals(viewModel.showSuccess.value, true)
            assertEquals(viewModel.showError.value, false)
            assertEquals(viewModel.showLoading.value, false)
            assertEquals(mockCharacterNamesModel, viewModel.characterNameList.value)
        }
    }

    @Test
    fun `onQueryTextSubmit should call search character use case with error`() {
        testScope.runBlockingTest {
            whenever(
                searchCharacterUseCase.executeUseCase(
                    any()
                )
            ).thenReturn(
                SearchCharacterUseCase.SearchCharacterResponse(characterModel = null, error = true)
            )
            whenever(characterNameMapper.toModel(any())).thenReturn(mockCharacterNamesModel)
            val argumentCaptor = argumentCaptor<SearchCharacterUseCase.SearchCharacterRequest>()
            viewModel.queryTextListener.onQueryTextSubmit("xyz")
            verify(searchCharacterUseCase).executeUseCase(
                argumentCaptor.capture()
            )
            assertEquals("xyz", argumentCaptor.firstValue.query)
            assertEquals(viewModel.showSuccess.value, false)
            assertEquals(viewModel.showError.value, true)
            assertEquals(viewModel.showLoading.value, false)
        }
    }

    @Test
    fun `onQueryTextSubmit should call search character use case with empty list response`() {
        testScope.runBlockingTest {
            whenever(
                searchCharacterUseCase.executeUseCase(
                    any()
                )
            ).thenReturn(
                SearchCharacterUseCase.SearchCharacterResponse(characterModel = emptyList(), error = false)
            )
            whenever(characterNameMapper.toModel(any())).thenReturn(mockCharacterNamesModel)
            val argumentCaptor = argumentCaptor<SearchCharacterUseCase.SearchCharacterRequest>()
            viewModel.queryTextListener.onQueryTextSubmit("xyz")
            verify(searchCharacterUseCase).executeUseCase(
                argumentCaptor.capture()
            )
            assertEquals("xyz", argumentCaptor.firstValue.query)
            assertEquals(viewModel.showSuccess.value, false)
            assertEquals(viewModel.showError.value, true)
            assertEquals(viewModel.showLoading.value, false)
        }
    }
}