package pl.mdanilowski.myapplication.dagger.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import pl.mdanilowski.myapplication.base.SpottedMobileApplication
import pl.mdanilowski.myapplication.dagger.module.ActivityBindingModule
import pl.mdanilowski.myapplication.dagger.module.ApplicationModule
import pl.mdanilowski.myapplication.dagger.module.UtilsModule
import pl.mdanilowski.myapplication.dagger.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        ViewModelModule::class,
        ApplicationModule::class,
        UtilsModule::class]
)
interface WishListApplicationComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: SpottedMobileApplication)
}