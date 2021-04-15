package com.example.starwars.presentation.mapper

import com.example.starwars.domain.model.SearchCharacterDetailsModel
import com.example.starwars.presentation.model.CharacterInfoModel

class CharacterInfoMapper :
    ModelMapper<SearchCharacterDetailsModel, CharacterInfoModel> {

    override fun toModel(request: SearchCharacterDetailsModel?): CharacterInfoModel {
        return CharacterInfoModel(
            request?.birth_year,
            request?.films?.map {
                getId(it)
            },
            request?.height,
            getId(request?.homeworld),
            request?.name,
            request?.species?.map {
                getId(it)
            }
        )
    }

    fun getId(url: String?): String {
        val array = url?.split("/")
        return array!![5]
    }
}