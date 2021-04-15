package com.example.starwars.presentation.ui.home

import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.MutableLiveData
import com.example.starwars.domain.model.SearchCharacterDetailsModel
import com.example.starwars.domain.usecase.SearchCharacterUseCase
import com.example.starwars.presentation.mapper.CharacterInfoMapper
import com.example.starwars.presentation.mapper.CharacterNameMapper
import com.example.starwars.presentation.model.CharacterInfoModel
import com.example.starwars.presentation.model.CharacterNamesModel
import com.example.starwars.presentation.ui.BaseViewModel
import com.example.starwars.presentation.ui.getViewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coroutineScopeProvider: CoroutineScope? = null,
    private val searchCharacterUseCase: SearchCharacterUseCase,
    private val characterInfoMapper: CharacterInfoMapper,
    private val characterNameMapper: CharacterNameMapper
) :
    BaseViewModel() {

    private val coroutineScope = getViewModelScope(coroutineScopeProvider)
    private var characterDetailList: List<SearchCharacterDetailsModel>? = null
    val characterNameList = MutableLiveData<CharacterNamesModel>()
    val characterDetail = MutableLiveData<CharacterInfoModel>()

    val onListItemClickListener: (position: Int) -> Unit = {
        characterDetail.postValue(
            characterInfoMapper.toModel(characterDetailList?.get(it))
        )
    }

    val queryTextListener = object : OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            searchCharacter(query)
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return true
        }

    }

    private fun searchCharacter(query: String?) {
        showSuccess.postValue(false)
        showError.postValue(false)
        showLoading.postValue(true)
        coroutineScope.launch {
            val response = searchCharacterUseCase.executeUseCase(
                SearchCharacterUseCase.SearchCharacterRequest(
                    query
                )
            )
            when (response.error) {
                true -> {
                    showSuccess.postValue(false)
                    showError.postValue(true)
                    showLoading.postValue(false)
                }
                false -> {
                    when (response.characterModel?.size?.compareTo(0)) {
                        0 -> {
                            showSuccess.postValue(false)
                            showError.postValue(true)
                            showLoading.postValue(false)
                        }
                        else -> {
                            showSuccess.postValue(true)
                            showLoading.postValue(false)
                            showError.postValue(false)
                            characterNameList.postValue(
                                characterNameMapper.toModel(response.characterModel?.map { it.first }
                                    ?.toList()))
                            characterDetailList = response.characterModel?.map { it.second }
                        }
                    }

                }
            }
        }
    }

}