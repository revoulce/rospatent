package ru.gov.rospatent.searchplatform.models

import com.google.gson.annotations.SerializedName


data class SearchRequest(
    @SerializedName(value = "q", alternate = ["qn"])
    val q: String
)