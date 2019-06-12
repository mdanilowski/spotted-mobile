package pl.mdanilowski.myapplication.data

import io.reactivex.Observable
import pl.mdanilowski.myapplication.data.model.Message
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/messages/{city}")
    fun fetchMessagesForCity(@Path("city") city: String): Observable<List<Message>>
}