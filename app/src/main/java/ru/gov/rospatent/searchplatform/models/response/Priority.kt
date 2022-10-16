package ru.gov.rospatent.searchplatform.models.response


import com.google.gson.annotations.SerializedName

data class Priority(
    @SerializedName("filing_date")
    val filingDate: String?,
    val number: String,
    @SerializedName("publishing_office")
    val publishingOffice: String?
)