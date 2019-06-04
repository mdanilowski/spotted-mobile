package pl.mdanilowski.myapplication.dagger.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.mdanilowski.myapplication.ui.dashboard.DashboardActivity
import pl.mdanilowski.myapplication.ui.splash.SplashActivity

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun bindDashboardActivity() : DashboardActivity

    @ContributesAndroidInjector
    abstract fun bindSplashActivity() : SplashActivity
}