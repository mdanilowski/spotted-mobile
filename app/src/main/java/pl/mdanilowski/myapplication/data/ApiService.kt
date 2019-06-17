package pl.mdanilowski.myapplication.data

import io.reactivex.Observable
import okhttp3.ResponseBody
import pl.mdanilowski.myapplication.data.model.Message
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/messages/{message}")
    fun fetchMessagesForCity(@Path("message") city: String): Observable<List<Message>>

    @POST("/messages/add")
    fun addMessage(@Body message: Message): Observable<ResponseBody>
}