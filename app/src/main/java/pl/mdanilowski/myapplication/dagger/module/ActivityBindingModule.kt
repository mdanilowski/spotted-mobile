package pl.mdanilowski.myapplication.dagger.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.mdanilowski.myapplication.ui.add.AddMessageActivity
import pl.mdanilowski.myapplication.ui.dashboard.DashboardActivity
import pl.mdanilowski.myapplication.ui.details.MessageDetailsActivity
import pl.mdanilowski.myapplication.ui.enterCity.EnterCityActivity
import pl.mdanilowski.myapplication.ui.splash.SplashActivity

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun bindEnterCityActivity(): EnterCityActivity

    @ContributesAndroidInjector
    abstract fun bindDashboardActivity(): DashboardActivity

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun bindMessageDetailsActivity(): MessageDetailsActivity

    @ContributesAndroidInjector
    abstract fun bindAddMessageActivity(): AddMessageActivity
}