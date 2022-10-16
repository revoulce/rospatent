package ru.gov.rospatent.searchplatform.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import ru.gov.rospatent.searchplatform.models.SearchRequest
import ru.gov.rospatent.searchplatform.models.SearchResponse

interface RetrofitApi {
    @Headers("Authorization: Bearer 26a213594e7f4f6e8cd89064d885ea93")
    @POST("search")
    fun postSearch(@Body searchRequest: SearchRequest?): Call<SearchResponse?>?
}