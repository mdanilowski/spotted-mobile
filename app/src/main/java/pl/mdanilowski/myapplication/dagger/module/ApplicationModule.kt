package pl.mdanilowski.myapplication.dagger.module

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import pl.mdanilowski.myapplication.base.WishListApplication
import pl.mdanilowski.myapplication.data.ApiService
import pl.mdanilowski.myapplication.util.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: WishListApplication) {

    private val API_URL: String = "localhost:3000"

    @Provides
    fun provideApp() = app

    @Provides
    fun provideContext(): Context = app

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }
}