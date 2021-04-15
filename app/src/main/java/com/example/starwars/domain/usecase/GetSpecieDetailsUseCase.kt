package com.example.starwars.domain.usecase

import com.example.starwars.domain.model.SpecieDetailsModel
import com.example.starwars.domain.repository.SpecieRepository
import javax.inject.Inject

class GetSpecieDetailsUseCase @Inject constructor(private val repository: SpecieRepository) :
    BaseUseCase<GetSpecieDetailsUseCase.GetSpecieDetailsRequest, GetSpecieDetailsUseCase.GetSpecieDetailsResponse>() {

    class GetSpecieDetailsRequest(val id: String) : Request
    class GetSpecieDetailsResponse(
        val specieDetailsModel: SpecieDetailsModel?,
        val error: Boolean = false
    ) : Response

    override suspend fun executeUseCase(request: GetSpecieDetailsRequest): GetSpecieDetailsResponse =
        repository.getSpecieDetails(request.id)
}