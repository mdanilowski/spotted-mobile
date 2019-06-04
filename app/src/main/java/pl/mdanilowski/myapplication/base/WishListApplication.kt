package pl.mdanilowski.myapplication.base

import android.annotation.SuppressLint
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import pl.mdanilowski.myapplication.dagger.AppInjector

@SuppressLint("Registered")
class WishListApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return AppInjector.init(this)
    }
}