package pl.mdanilowski.spotted.application.posts.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import pl.mdanilowski.spotted.application.posts.data.entity.PostEntity
import pl.mdanilowski.spotted.application.posts.data.entity.PostWithTagsAndComments

@Dao
interface PostDao {

    @Transaction
    @Query("SELECT * FROM POST WHERE owningCityId=:cityId")
    fun getPostsForCity(cityId: Long): LiveData<List<PostWithTagsAndComments>>

    @Query("DELETE FROM POST WHERE owningCityId=:cityId")
    fun deletePostsFromCity(cityId: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<PostEntity>)

    @Transaction
    fun deletePostsFromCityAndAddNewPosts(cityId: Long, posts: List<PostEntity>) {
        deletePostsFromCity(cityId)
        insertPosts(posts)
    }
}
