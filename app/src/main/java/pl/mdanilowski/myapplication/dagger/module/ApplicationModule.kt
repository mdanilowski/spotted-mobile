package pl.mdanilowski.myapplication.dagger.module

import android.content.Context
import dagger.Module
import dagger.Provides
import pl.mdanilowski.myapplication.base.SpottedMobileApplication
import pl.mdanilowski.myapplication.data.ApiService
import pl.mdanilowski.myapplication.util.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: SpottedMobileApplication) {

    @Provides
    fun provideApp() = app

    @Provides
    fun provideContext(): Context = app

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BACKEND_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}