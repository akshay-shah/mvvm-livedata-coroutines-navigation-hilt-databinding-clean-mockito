package com.example.starwars.domain.usecase

import com.example.starwars.domain.model.PlanetDetailsModel
import com.example.starwars.domain.repository.PlanetRepository
import javax.inject.Inject

class GetPlanetDetailsUseCase @Inject constructor(private val repository: PlanetRepository) :
    BaseUseCase<GetPlanetDetailsUseCase.GetPlanetDetailsRequest, GetPlanetDetailsUseCase.GetPlanetDetailsResponse>() {

    class GetPlanetDetailsRequest(val id: String) : Request
    class GetPlanetDetailsResponse(
        val planetDetailsModel: PlanetDetailsModel?,
        val error: Boolean = false
    ) : Response

    override suspend fun executeUseCase(request: GetPlanetDetailsRequest): GetPlanetDetailsResponse =
        repository.getPlanetDetails(request.id)
}