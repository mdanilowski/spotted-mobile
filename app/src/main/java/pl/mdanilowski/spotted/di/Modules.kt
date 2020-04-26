package pl.mdanilowski.spotted.di

import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.mdanilowski.spotted.BuildConfig
import pl.mdanilowski.spotted.data.ApiService
import pl.mdanilowski.spotted.data.db.SpottedDB
import pl.mdanilowski.spotted.data.db.dao.CityDao
import pl.mdanilowski.spotted.data.interactors.DatabaseInteractor
import pl.mdanilowski.spotted.data.interactors.DatabaseInteractorImpl
import pl.mdanilowski.spotted.data.interactors.NetworkInteractor
import pl.mdanilowski.spotted.data.interactors.NetworkInteractorImpl
import pl.mdanilowski.spotted.repository.CitiesRepository
import pl.mdanilowski.spotted.repository.CitiesRepositoryImpl
import pl.mdanilowski.spotted.ui.add.AddMessageViewModel
import pl.mdanilowski.spotted.ui.dashboard.DashboardViewModel
import pl.mdanilowski.spotted.ui.details.MessageDetailsViewModel
import pl.mdanilowski.spotted.ui.enterCity.EnterCityViewModel
import pl.mdanilowski.spotted.util.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModule = module(override = true) {
    viewModel { EnterCityViewModel(get(), get(), get()) }
    viewModel { DashboardViewModel(get(), get(), get(), get()) }
    viewModel { AddMessageViewModel(get(), get(), get(), get()) }
    viewModel { MessageDetailsViewModel(get()) }
}

val repositoryModule = module(override = true) {
    single { Room.databaseBuilder(androidContext(), SpottedDB::class.java, SpottedDB.DB_NAME).build() }
    single<BaseSchedulers> { BaseSchedulersImpl() }

    // Dao
    factory<CityDao> { get<SpottedDB>().citiesDao() }

    // Interactors
    factory<DatabaseInteractor> { DatabaseInteractorImpl(get()) }
    factory<NetworkInteractor> { NetworkInteractorImpl(get(), get()) }

    // Repos
    factory<CitiesRepository> { CitiesRepositoryImpl(get(), get()) }
}

val useCaseModule = module(override = true) {
    factory<StringProvider> { ContextStringProvider(androidContext()) }
    factory<ErrorHandler> { ErrorHandlerImpl(get()) }
    single<StorageUtil> { SpottedSimpleStorage(androidContext()) }
}

val networkModule = module(override = true) {
    fun provideMoshi() = Moshi.Builder()
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
