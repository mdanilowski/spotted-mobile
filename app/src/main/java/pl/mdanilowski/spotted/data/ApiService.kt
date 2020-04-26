package pl.mdanilowski.spotted.data

import io.reactivex.Single
import okhttp3.ResponseBody
import pl.mdanilowski.spotted.data.model.City
import pl.mdanilowski.spotted.data.model.Message
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/messages/{message}")
    fun fetchMessagesForCity(@Path("message") city: String): Single<List<Message>>

    @POST("/messages/add")
    fun addMessage(@Body message: Message): Single<ResponseBody>

    @GET("/cities/")
    fun getAllAvailableCities() : Single<List<City>>
}