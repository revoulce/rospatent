package ru.gov.rospatent.searchplatform.models.response


import com.google.gson.annotations.SerializedName

data class Common(
    val application: Application,
    val classification: Classification,
    @SerializedName("document_number")
    val documentNumber: String,
    val kind: String,
    val priority: List<Priority>?,
    @SerializedName("publication_date")
    val publicationDate: String,
    @SerializedName("publishing_office")
    val publishingOffice: String
)