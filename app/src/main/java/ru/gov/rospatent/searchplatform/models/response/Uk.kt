package ru.gov.rospatent.searchplatform.models.response


data class Uk(
    val applicant: List<Applicant>,
    val inventor: List<Inventor>,
    val patentee: List<Patentee>,
    val title: String
)