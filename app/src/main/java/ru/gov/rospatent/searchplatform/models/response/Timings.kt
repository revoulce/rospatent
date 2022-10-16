package ru.gov.rospatent.searchplatform.models.response


data class Timings(
    val overall: Double,
    val postprocessing: Double,
    val preprocessing: Double,
    val search: Double
)