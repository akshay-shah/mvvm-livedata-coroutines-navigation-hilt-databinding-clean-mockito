package com.example.starwars.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterInfoModel(
    val birth_year: String?,
    val filmsIdList: List<String>?,
    val height: String?,
    val homeworldId: String?,
    val name: String?,
    val specieIdList: List<String>?
) : Parcelable

@Parcelize
data class CharacterNamesModel(val characterNamesList: List<String>?) : Parcelable

@Parcelize
data class CharacterDetailsModel(
    val birth_year: String = "",
    val filmDetails: String = "",
    val height: String = "",
    val name: String = "",
    val specieName: String = "",
    val language: String = "",
    val homeworld: String = "",
    val planetPopulation: String = "",
) : Parcelable