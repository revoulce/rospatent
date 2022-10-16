package ru.gov.rospatent.searchplatform.models.response


import com.google.gson.annotations.SerializedName

data class Application(
    @SerializedName("filing_date")
    val filingDate: String,
    val number: String
)