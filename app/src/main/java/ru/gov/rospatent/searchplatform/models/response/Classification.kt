package ru.gov.rospatent.searchplatform.models.response


data class Classification(
    val cpc: List<Cpc>?,
    val ipc: List<Ipc>
)