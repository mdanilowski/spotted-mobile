package pl.mdanilowski.myapplication.dagger.module

import dagger.Binds
import dagger.Module
import pl.mdanilowski.myapplication.util.*
import javax.inject.Singleton

@Module
abstract class UtilsModule {

    @Binds
    @Singleton
    abstract fun bindBaseSchedulers(baseSchedulersImpl: BaseSchedulersImpl): BaseSchedulers

    @Binds
    @Singleton
    abstract fun bindStringsProvider(contextStringProvider: ContextStringProvider): StringProvider

    @Binds
    @Singleton
    abstract fun bindErrorHandler(errorHandlerImpl: ErrorHandlerImpl): ErrorHandler
}