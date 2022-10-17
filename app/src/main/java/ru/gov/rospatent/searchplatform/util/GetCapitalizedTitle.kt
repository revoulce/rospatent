package ru.gov.rospatent.searchplatform.util

import ru.gov.rospatent.searchplatform.models.response.Hit

fun getCapitalizedTitle(patent: Hit): String {
    return if (patent.biblio.ru != null) {
        patent.biblio.ru.title
    } else if (patent.biblio.en?.title != null) {
        patent.biblio.en.title
    } else {
        patent.snippet.title
    }
}