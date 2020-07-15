package pl.mdanilowski.spotted.application.posts.domain.usecase

import androidx.lifecycle.LiveData
import io.reactivex.Completable
import pl.mdanilowski.spotted.application.posts.data.entity.PostWithTagsAndComments
import pl.mdanilowski.spotted.application.posts.domain.repository.PostsRepository

interface GetPostsUseCase {

    fun getPostsForCity(cityId: Long): LiveData<List<PostWithTagsAndComments>>

    fun fetchAndUpdatePostsForCity(cityId: Long): Completable
}

class GetPostsUseCaseImpl(private val postsRepository: PostsRepository) : GetPostsUseCase {

    override fun getPostsForCity(cityId: Long): LiveData<List<PostWithTagsAndComments>> =
        postsRepository.getAllPostsForCity(cityId)

    override fun fetchAndUpdatePostsForCity(cityId: Long): Completable =
        postsRepository.updateCityPosts(cityId)
}