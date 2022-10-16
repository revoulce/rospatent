package ru.gov.rospatent.searchplatform.models.response


import com.google.gson.annotations.SerializedName

data class Ipc(
    @SerializedName("class")
    val classX: String,
    @SerializedName("classification_value")
    val classificationValue: String,
    val fullname: String,
    @SerializedName("main_group")
    val mainGroup: String,
    val section: String,
    val subclass: String,
    val subgroup: String
)