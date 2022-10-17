package ru.gov.rospatent.searchplatform.util

import ru.gov.rospatent.searchplatform.models.response.Hit

fun getAuthors(patent: Hit): String? {
    return if (patent.biblio.ru != null) {
        patent.biblio.ru.inventor.joinToString(separator = ", ") {
            it.name
        }
    } else if (patent.biblio.en != null) {
        patent.biblio.en.inventor.joinToString(separator = ", ") {
            it.name
        }
    } else {
        patent.snippet.inventor
    }
}