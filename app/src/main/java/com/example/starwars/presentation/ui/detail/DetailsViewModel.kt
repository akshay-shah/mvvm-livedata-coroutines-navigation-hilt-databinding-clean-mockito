package com.example.starwars.presentation.ui.detail

import androidx.lifecycle.MutableLiveData
import com.example.starwars.domain.usecase.GetFilmDetailsUseCase
import com.example.starwars.domain.usecase.GetPlanetDetailsUseCase
import com.example.starwars.domain.usecase.GetSpecieDetailsUseCase
import com.example.starwars.presentation.mapper.CharacterDetailMapper
import com.example.starwars.presentation.model.CharacterDetailsModel
import com.example.starwars.presentation.model.CharacterInfoModel
import com.example.starwars.presentation.ui.BaseViewModel
import com.example.starwars.presentation.ui.getViewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val coroutineScopeProvider: CoroutineScope? = null,
    private val getSpecieDetailsUseCase: GetSpecieDetailsUseCase,
    private val getFilmDetailsUseCase: GetFilmDetailsUseCase,
    private val getPlanetDetailsUseCase: GetPlanetDetailsUseCase,
    private val mapper: CharacterDetailMapper
) : BaseViewModel() {

    var characterDetailsModel = MutableLiveData<CharacterDetailsModel?>()
    private val coroutineScope = getViewModelScope(coroutineScopeProvider)

    fun init(infoModel: CharacterInfoModel?) {
        coroutineScope.launch {
            showLoading.postValue(true)
            infoModel?.let {
                val specieResponse = async {
                    it.specieIdList?.map {
                        getSpecieDetailsUseCase.executeUseCase(
                            GetSpecieDetailsUseCase.GetSpecieDetailsRequest(
                                it
                            )
                        )
                    }
                }
                val filmResponse = async {
                    it.filmsIdList?.map {
                        getFilmDetailsUseCase.executeUseCase(
                            GetFilmDetailsUseCase.GetFilmDetailsRequest(
                                it
                            )
                        )
                    }
                }
                val planetResponse = it.homeworldId?.let {
                    getPlanetDetailsUseCase.executeUseCase(
                        GetPlanetDetailsUseCase.GetPlanetDetailsRequest(
                            it
                        )
                    )
                }
                showLoading.postValue(false)
                characterDetailsModel.postValue(
                    mapper.toModel(
                        name = it.name.toString(),
                        birth_year = it.birth_year.toString(),
                        height = it.height.toString(),
                        specieDetailsResponse = specieResponse.await(),
                        filmDetailsResponse = filmResponse.await(),
                        planetDetailsResponse = planetResponse
                    )
                )
            }
        }
    }
}