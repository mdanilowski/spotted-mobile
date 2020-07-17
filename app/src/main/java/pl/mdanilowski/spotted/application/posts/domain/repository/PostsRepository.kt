package pl.mdanilowski.spotted.application.posts.domain.repository

import androidx.lifecycle.LiveData
import io.reactivex.Completable
import pl.mdanilowski.spotted.application.cities.data.dao.CityDao
import pl.mdanilowski.spotted.application.posts.data.dao.PostDao
import pl.mdanilowski.spotted.application.posts.data.entity.PostEntity
import pl.mdanilowski.spotted.application.posts.data.entity.PostWithTagsAndComments
import pl.mdanilowski.spotted.application.posts.domain.model.Post
import pl.mdanilowski.spotted.network.ApiService

interface PostsRepository {

    fun getAllPostsForCity(cityId: Long): LiveData<List<PostWithTagsAndComments>>

    fun updateCityPosts(cityId: Long): Completable
}

class PostsRepositoryImpl(
    private val postDao: PostDao,
    private val cityDao: CityDao,
    private val apiService: ApiService
) :
    PostsRepository {

    override fun getAllPostsForCity(cityId: Long) = postDao.getPostsForCity(cityId)

    override fun updateCityPosts(cityId: Long): Completable {
        return cityDao.getCityName(cityId)
            .flatMap { name -> apiService.fetchMessagesForCity(name) }
            .flatMapCompletable { posts ->
                Completable.fromAction {
                    postDao.deletePostsFromCityAndAddNewPosts(
                        cityId,
                        mapToPostEntities(posts, cityId)
                    )
                }
            }
    }

    private fun mapToPostEntities(posts: List<Post>, cityId: Long): List<PostEntity> =
        posts.map { PostEntity(it.message, cityId, it.date) }.toList()
}