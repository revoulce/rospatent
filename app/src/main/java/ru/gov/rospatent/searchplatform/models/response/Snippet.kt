package ru.gov.rospatent.searchplatform.models.response


data class Snippet(
    val applicant: String?,
    val classification: ClassificationX,
    val description: String,
    val inventor: String?,
    val lang: String,
    val patentee: String?,
    val title: String
)