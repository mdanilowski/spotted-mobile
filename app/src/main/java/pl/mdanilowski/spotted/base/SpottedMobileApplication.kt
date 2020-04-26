package pl.mdanilowski.spotted.base

import android.annotation.SuppressLint
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import pl.mdanilowski.spotted.di.*
import timber.log.Timber

@SuppressLint("Registered")
class SpottedMobileApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initLogger()
        initKoin()
    }

    private fun initLogger() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@SpottedMobileApplication)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    useCaseModule,
                    networkModule
                )
            )
        }
    }
}