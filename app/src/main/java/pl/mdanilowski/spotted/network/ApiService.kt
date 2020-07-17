package pl.mdanilowski.spotted.network

import io.reactivex.Single
import okhttp3.ResponseBody
import pl.mdanilowski.spotted.application.cities.domain.model.City
import pl.mdanilowski.spotted.application.posts.domain.model.Post
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/messages/{city}")
    fun fetchMessagesForCity(@Path("city") city: String): Single<List<Post>>

    @POST("/messages/add")
    fun addMessage(@Body post: Post): Single<ResponseBody>

    @GET("/cities/")
    fun getAllAvailableCities() : Single<List<City>>
}