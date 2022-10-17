package ru.gov.rospatent.searchplatform.ui.screens.main

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gov.rospatent.searchplatform.api.RetrofitApi
import ru.gov.rospatent.searchplatform.models.SearchRequest
import ru.gov.rospatent.searchplatform.models.SearchResponse
import ru.gov.rospatent.searchplatform.models.response.Hit

fun searchPatents(searchString: String, result: SnapshotStateList<Hit>) {
    val url = "https://searchplatform.rospatent.gov.ru/patsearch/v0.2/"

    val retrofit =
        Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()

    val retrofitApi = retrofit.create(RetrofitApi::class.java)
    val searchRequest = SearchRequest(searchString)
    val call: Call<SearchResponse?>? = retrofitApi.postSearch(searchRequest)

    call!!.enqueue(object : Callback<SearchResponse?> {
        override fun onResponse(
            call: Call<SearchResponse?>, response: Response<SearchResponse?>
        ) {
            val model: SearchResponse? = response.body()

            val resp = "Response Code: " + response.code() + "\nAvailable: " + (model?.available
                ?: "Error") + "\nTotal: " + (model?.total ?: "Error")
            Log.i("API", resp)

            if (model != null) {
                result.clear()
                result.addAll(model.hits)
            }
        }

        override fun onFailure(call: Call<SearchResponse?>, t: Throwable) {
            t.message?.let { Log.e("API", it) }
        }
    })
}
