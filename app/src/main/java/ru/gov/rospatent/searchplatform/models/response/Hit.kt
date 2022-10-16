package ru.gov.rospatent.searchplatform.models.response


import com.google.gson.annotations.SerializedName

data class Hit(
    val biblio: Biblio,
    val common: Common,
    val dataset: String,
    val drawings: List<Drawing>?,
    val id: String,
    val index: String,
    val meta: Meta,
    val similarity: Double,
    @SerializedName("similarity_norm")
    val similarityNorm: Double,
    val snippet: Snippet
)