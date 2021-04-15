package com.example.starwars.domain.model

data class SearchCharacterNameModel(val name: String)

data class SearchCharacterDetailsModel(
    val birth_year: String,
    val films: List<String>,
    val height: String,
    val homeworld: String,
    val name: String,
    val species: List<String>
)

data class PlanetDetailsModel(val name: String, val population: String)

data class FilmDetailsModel(val name: String, val descripton: String)

data class SpecieDetailsModel(val name: String, val language: String)