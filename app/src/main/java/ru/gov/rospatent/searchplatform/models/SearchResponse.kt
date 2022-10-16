package ru.gov.rospatent.searchplatform.models


import ru.gov.rospatent.searchplatform.models.response.Hit
import ru.gov.rospatent.searchplatform.models.response.Timings

data class SearchResponse(
    val available: Int,
    val hits: List<Hit>,
    val timings: Timings,
    val total: Int
)