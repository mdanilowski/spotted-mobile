package pl.mdanilowski.spotted.di

import androidx.room.Room
import com.google.gson.internal.bind.DateTypeAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.mdanilowski.spotted.BuildConfig
import pl.mdanilowski.spotted.application.cities.domain.repository.CitiesRepository
import pl.mdanilowski.spotted.application.cities.domain.repository.CitiesRepositoryImpl
import pl.mdanilowski.spotted.application.cities.domain.usecase.CityLocalStorageUseCase
import pl.mdanilowski.spotted.application.cities.domain.usecase.CityLocalStorageUseCaseImpl
import pl.mdanilowski.spotted.application.cities.domain.usecase.GetCitiesUseCase
import pl.mdanilowski.spotted.application.cities.domain.usecase.GetCitiesUseCaseImpl
import pl.mdanilowski.spotted.application.cities.presentation.citySelect.CitySelectViewModel
import pl.mdanilowski.spotted.application.posts.domain.repository.PostsRepository
import pl.mdanilowski.spotted.application.posts.domain.repository.PostsRepositoryImpl
import pl.mdanilowski.spotted.application.posts.domain.usecase.GetPostsUseCase
import pl.mdanilowski.spotted.application.posts.domain.usecase.GetPostsUseCaseImpl
import pl.mdanilowski.spotted.application.posts.presentation.addPost.AddPostViewModel
import pl.mdanilowski.spotted.application.posts.presentation.post.PostViewModel
import pl.mdanilowski.spotted.application.posts.presentation.postsDashboard.PostsDashboardViewModel
import pl.mdanilowski.spotted.base.SpottedDB
import pl.mdanilowski.spotted.network.ApiService
import pl.mdanilowski.spotted.util.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

val viewModelModule = module(override = true) {
    viewModel { CitySelectViewModel(get(), get(), get()) }
    viewModel {
        PostsDashboardViewModel(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModel {
        AddPostViewModel(
            get(),
            get(),
            get()
        )
    }
    viewModel {
        PostViewModel(
            get(),
            get()
        )
    }
}

val dbModule = module(override = true) {
    single { Room.databaseBuilder(androidContext(), SpottedDB::class.java, SpottedDB.DB_NAME).build() }

    // City Dao
    factory { get<SpottedDB>().cityDao() }
    factory { get<SpottedDB>().postDao() }
}

val repositoryModule = module(override = true) {
    single<BaseSchedulers> { BaseSchedulersImpl() }

    // Repos
    factory<CitiesRepository> { CitiesRepositoryImpl(get(), get(), get()) }
    factory<PostsRepository> { PostsRepositoryImpl(get(), get(), get()) }
}

val useCaseModule = module(override = true) {
    // Get cities use case
    factory<GetCitiesUseCase> { GetCitiesUseCaseImpl(get()) }
    factory<GetPostsUseCase> { GetPostsUseCaseImpl(get()) }
    factory<CityLocalStorageUseCase> { CityLocalStorageUseCaseImpl(get()) }
}

val utilModule = module(override = true) {
    factory<StringProvider> { ContextStringProvider(androidContext()) }
    factory<ErrorHandler> { ErrorHandlerImpl(get()) }
    single<StorageUtil> { SpottedSimpleStorage(androidContext()) }
}

val networkModule = module(override = true) {
    fun provideMoshi() = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .add(KotlinJsonAdapterFactory())
        .build()

    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .readTimeout(TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .writeTimeout(TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()


    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient) = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build()

    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    single { provideMoshi() }
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get(), get()) }
    single { provideApiService(get()) }
}

private const val BASE_URL = BuildConfig.API_URL
private const val TIMEOUT_MS = 1000 * 30L
