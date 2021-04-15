package com.example.starwars.data.mapper

import com.example.starwars.domain.usecase.BaseUseCase

interface ResponseMapper<P, Q : BaseUseCase.Response> {
    fun toModel(response: P?): Q
}