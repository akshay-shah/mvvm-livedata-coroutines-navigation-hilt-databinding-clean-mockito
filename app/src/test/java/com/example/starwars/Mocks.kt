package com.example.starwars

import com.example.starwars.data.model.*
import com.example.starwars.domain.model.*
import com.example.starwars.domain.usecase.GetFilmDetailsUseCase
import com.example.starwars.domain.usecase.GetPlanetDetailsUseCase
import com.example.starwars.domain.usecase.GetSpecieDetailsUseCase
import com.example.starwars.domain.usecase.SearchCharacterUseCase
import com.example.starwars.presentation.model.CharacterDetailsModel
import com.example.starwars.presentation.model.CharacterInfoModel
import com.example.starwars.presentation.model.CharacterNamesModel

val mockCharacterInfoModel = CharacterInfoModel(
    "birth_year",
    listOf("1", "2"),
    "height",
    "homeWorld",
    "name",
    listOf("1", "2")
)

val mockSearchCharacterResponse = SearchCharacterUseCase.SearchCharacterResponse(
    characterModel = listOf(
        SearchCharacterNameModel("Test") to SearchCharacterDetailsModel(
            "birth_year",
            listOf("1", "2"),
            "height",
            "homeWorld",
            "Test",
            listOf("1", "2")
        ),
        SearchCharacterNameModel("Test") to SearchCharacterDetailsModel(
            "birth_year",
            listOf("1", "2"),
            "height",
            "homeWorld",
            "Test",
            listOf("1", "2")
        )
    ),
    error = false
)

val mockCharacterNamesModel = CharacterNamesModel(listOf("Test", "Test"))

val mockCharacterDetailModel = listOf(
    CharacterInfoModel(
        "birth_year",
        listOf("1", "2"),
        "height",
        "homeWorld",
        "name",
        listOf("1", "2")
    ),
    CharacterInfoModel(
        "birth_year",
        listOf("1", "2"),
        "height",
        "homeWorld",
        "name",
        listOf("1", "2")
    )
)

val mockFilmDetailsModel = FilmDetailsModel(name = "Film", descripton = "Description")

val mockSpecieDetailsModel = SpecieDetailsModel(name = "SpecieName", language = "Language")

val mockPlanetDetailsModel = PlanetDetailsModel(name = "PlanetName", population = "Population")

val mockFilmDetailsResponse = GetFilmDetailsUseCase.GetFilmDetailsResponse(mockFilmDetailsModel)

val mockSpecieDetailsResponse =
    GetSpecieDetailsUseCase.GetSpecieDetailsResponse(mockSpecieDetailsModel)

val mockPlanetDetailsResponse = GetPlanetDetailsUseCase.GetPlanetDetailsResponse(
    mockPlanetDetailsModel
)

val mockCharacterDetailsModel = CharacterDetailsModel(
    birth_year = "birth_year",
    filmDetails = "filmDetails",
    height = "height",
    name = "name",
    specieName = "specieName",
    language = "language",
    homeworld = "homeWorld",
    planetPopulation = "population"
)

val mockSearchCharacterNameModelList =
    listOf(SearchCharacterNameModel("Test"), SearchCharacterNameModel("Test"))

val mockSearchCharacterDetailsModel = SearchCharacterDetailsModel(
    "birth_year",
    listOf("/////1", "/////2"),
    "height",
    "/////homeWorld",
    "name",
    listOf("/////1", "/////2")
)

val mockModelCharacterDetails = CharacterDetailsModel(
    birth_year = "Birth Year - birth_year \n",
    filmDetails = "Film Details \n\n\n Film Name - Film\n Description - Description\n\n",
    height = "Height - height \n",
    name = "Character Name - name \n",
    specieName = "Specie Name\nSpecieName\n",
    language = "Languages \nLanguage\n",
    homeworld = "HomeWorld - PlanetName\n",
    planetPopulation = "Planet Population - Population\n"
)

val mockFilmResponse = FilmResponse(
    listOf("character1"),
    "created",
    "director",
    "edited",
    1,
    "Description",
    listOf("planet1"),
    "producer",
    "release_date",
    listOf("specie1"),
    listOf("starship1"),
    "Film",
    "url",
    listOf("vehicle1")
)

val mockPlanetResponse = PlanetResponse(
    "climate",
    "created",
    "diameter",
    "edited",
    listOf("film1"),
    "gravity",
    "PlanetName",
    "orbital_period",
    "Population",
    listOf("resident1"),
    "rotation_period",
    "surface_water",
    "terrain",
    "url"
)

val mockSearchResponse = SearchResponse(
    2, "next", "previous", listOf(
        Result(
            "birth_year",
            "created",
            "edited",
            "eye_color",
            listOf("1", "2"),
            "gender",
            "hair_color",
            "height",
            "homeWorld",
            "mass",
            "Test",
            "skin_color",
            listOf("1", "2"),
            listOf("starship1"),
            "url",
            listOf("vehicle1")
        ),
        Result(
            "birth_year",
            "created",
            "edited",
            "eye_color",
            listOf("1", "2"),
            "gender",
            "hair_color",
            "height",
            "homeWorld",
            "mass",
            "Test",
            "skin_color",
            listOf("1", "2"),
            listOf("starship1"),
            "url",
            listOf("vehicle1")
        )
    )
)

val mockSpecieResponse = SpecieResponse(
    "height",
    "lifespan",
    "classification",
    "created",
    "designation",
    "edited",
    "eye_color",
    listOf("1", "2"),
    "hair_color",
    "homeWorld",
    "Language",
    "SpecieName",
    listOf("people1"),
    "skin_color",
    "url"
)

