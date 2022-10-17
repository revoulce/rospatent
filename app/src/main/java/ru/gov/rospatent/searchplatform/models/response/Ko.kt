package ru.gov.rospatent.searchplatform.models.response

import com.google.gson.annotations.SerializedName

data class Ko(
    val applicant: List<Applicant>?,
    val citations: String?,
    @SerializedName("citations_parsed")
    val citationsParsed: List<CitationsParsed>?,
    val inventor: List<Inventor>,
    val patentee: List<Patentee>?,
    val title: String?
)
