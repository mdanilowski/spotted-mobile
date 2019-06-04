package pl.mdanilowski.myapplication.dagger.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.mdanilowski.myapplication.dagger.ViewModelFactory
import pl.mdanilowski.myapplication.dagger.util.ViewModelKey
import pl.mdanilowski.myapplication.ui.dashboard.DashboardViewModel
import pl.mdanilowski.myapplication.ui.enterCity.EnterCityViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(dashboardViewModel: DashboardViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EnterCityViewModel::class)
    abstract fun bindEnterCityViewModel(enterCityViewModel: EnterCityViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory
}