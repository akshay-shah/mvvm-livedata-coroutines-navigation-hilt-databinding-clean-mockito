package com.example.starwars.presentation.mapper

import com.example.starwars.domain.usecase.GetFilmDetailsUseCase
import com.example.starwars.domain.usecase.GetPlanetDetailsUseCase
import com.example.starwars.domain.usecase.GetSpecieDetailsUseCase
import com.example.starwars.presentation.model.CharacterDetailsModel


class CharacterDetailMapper {
    fun toModel(
        birth_year: String,
        height: String,
        name: String,
        specieDetailsResponse: List<GetSpecieDetailsUseCase.GetSpecieDetailsResponse>?,
        filmDetailsResponse: List<GetFilmDetailsUseCase.GetFilmDetailsResponse>?,
        planetDetailsResponse: GetPlanetDetailsUseCase.GetPlanetDetailsResponse?
    ): CharacterDetailsModel {
        return CharacterDetailsModel(
            birth_year = "Birth Year - $birth_year \n",
            filmDetails = formatFilmResponse(
                StringBuilder().append("Film Details \n\n"),
                filmDetailsResponse
            ),
            height = "Height - $height \n",
            name = "Character Name - $name \n",
            specieName = formatSpecieResponse(
                StringBuilder().append("Specie Name\n"),
                specieDetailsResponse
            ),
            language = formatLanguageResponse(
                StringBuilder().append("Languages \n"),
                specieDetailsResponse
            ),
            homeworld = "HomeWorld - " + planetDetailsResponse?.planetDetailsModel?.name + "\n",
            planetPopulation = "Planet Population - " + planetDetailsResponse?.planetDetailsModel?.population + "\n"
        )
    }

    private fun formatSpecieResponse(
        string: StringBuilder,
        specieDetailsResponse: List<GetSpecieDetailsUseCase.GetSpecieDetailsResponse>?
    ): String {
        specieDetailsResponse?.forEach {
            string.append(it.specieDetailsModel?.name)
                .append("\n")
        }
        return string.toString()
    }

    private fun formatFilmResponse(
        string: java.lang.StringBuilder,
        filmDetailsResponse: List<GetFilmDetailsUseCase.GetFilmDetailsResponse>?
    ): String {
        filmDetailsResponse?.forEach {
            string.append("\n Film Name - ")
                .append(it.filmDetailsModel?.name)
                .append("\n Description - ")
                .append(it.filmDetailsModel?.descripton)
                .append("\n\n")
        }
        return string.toString()
    }

    private fun formatLanguageResponse(
        string: java.lang.StringBuilder,
        specieDetailsResponse: List<GetSpecieDetailsUseCase.GetSpecieDetailsResponse>?
    ): String {
        specieDetailsResponse?.forEach {
            string.append(it.specieDetailsModel?.language)
                .append("\n")
        }
        return string.toString()
    }

}