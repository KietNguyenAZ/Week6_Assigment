package com.example.onboarding

data class TopRateRes(
    val page: Long? = null,
    val results: List<Movie>? = null,
    val totalPages: Long? = null,
    val totalResults: Long? = null
)
