package pl.mdanilowski.spotted.application.posts.presentation.postsDashboard

import androidx.lifecycle.LiveData
import pl.mdanilowski.spotted.application.cities.domain.usecase.CityLocalStorageUseCase
import pl.mdanilowski.spotted.application.cities.domain.usecase.GetCitiesUseCase
import pl.mdanilowski.spotted.application.posts.data.entity.PostWithTagsAndComments
import pl.mdanilowski.spotted.base.BaseViewModel
import pl.mdanilowski.spotted.application.posts.domain.usecase.GetPostsUseCase
import pl.mdanilowski.spotted.util.BaseSchedulers
import pl.mdanilowski.spotted.util.ErrorHandler
import timber.log.Timber

class PostsDashboardViewModel(
    private val postsUseCase: GetPostsUseCase,
    private val citiesUseCase: GetCitiesUseCase,
    private val cityLocalStorageUseCase: CityLocalStorageUseCase,
    baseSchedulers: BaseSchedulers,
    errorHandler: ErrorHandler
) : BaseViewModel(baseSchedulers, errorHandler) {

    private var cityId: Long? = null
    lateinit var cityName: LiveData<String>
    lateinit var posts: LiveData<List<PostWithTagsAndComments>>

    fun setSelectedCityAndFetchPosts(cityId: Long) {
        this.cityId = cityId
        cityName = citiesUseCase.getSelectedCityName(cityId)
        cityLocalStorageUseCase.updateSelectedCityId(cityId)
        posts = postsUseCase.getPostsForCity(cityId)
    }

    fun fetchNewPostsForCity() {
        cityId?.let {
            postsUseCase.fetchAndUpdatePostsForCity(it)
                .subscribeOn(baseSchedulers.io())
                .observeOn(baseSchedulers.main())
                .doOnError { e -> Timber.e(e) }
                .subscribe {
                    Timber.i("NEW POSTS LOADED")
                }
        }
    }
}