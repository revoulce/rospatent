package ru.gov.rospatent.searchplatform.util

import ru.gov.rospatent.searchplatform.models.response.Hit

fun getPatentee(patent: Hit): String? {
    return if (patent.biblio.ru != null) {
        patent.biblio.ru.patentee?.joinToString(separator = ", ") {
            it.name
        }
    } else if (patent.biblio.en != null) {
        patent.biblio.en.patentee?.joinToString(separator = ", ") {
            it.name
        }
    } else {
        patent.snippet.patentee
    }
}