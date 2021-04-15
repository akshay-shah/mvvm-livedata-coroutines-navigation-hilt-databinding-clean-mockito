package com.example.starwars.presentation.mapper

interface ModelMapper<P, Q> {
    fun toModel(request: P?): Q
}