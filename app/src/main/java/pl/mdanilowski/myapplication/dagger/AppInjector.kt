package pl.mdanilowski.myapplication.dagger

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

import com.byoutline.secretsauce.di.Injectable
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import pl.mdanilowski.myapplication.base.SpottedMobileApplication
import pl.mdanilowski.myapplication.dagger.component.DaggerWishListApplicationComponent
import pl.mdanilowski.myapplication.dagger.component.WishListApplicationComponent
import pl.mdanilowski.myapplication.dagger.module.ApplicationModule

object AppInjector {

    fun init(
        app: SpottedMobileApplication,
        graphCreator: (SpottedMobileApplication) -> WishListApplicationComponent = { buildGraph(it) }
    ): WishListApplicationComponent {

        val appComponent = graphCreator(app)

        app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                handleActivity(activity)
            }

            override fun onActivityStarted(activity: Activity) {}

            override fun onActivityResumed(activity: Activity) {}

            override fun onActivityPaused(activity: Activity) {}

            override fun onActivityStopped(activity: Activity) {}

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

            override fun onActivityDestroyed(activity: Activity) {}
        })
        return appComponent
    }

    private fun buildGraph(app: SpottedMobileApplication): WishListApplicationComponent {
        val appComponent = DaggerWishListApplicationComponent.builder()
            .applicationModule(ApplicationModule(app))
            .build()
        appComponent.inject(app)
        return appComponent
    }

    private fun handleActivity(activity: Activity) {
        if (activity is HasSupportFragmentInjector || activity is Injectable) {
            AndroidInjection.inject(activity)
            (activity as? FragmentActivity)?.supportFragmentManager?.registerFragmentLifecycleCallbacks(
                object : FragmentManager.FragmentLifecycleCallbacks() {
                    override fun onFragmentCreated(
                        fm: FragmentManager,
                        f: Fragment,
                        savedInstanceState: Bundle?
                    ) {
                        if (f is Injectable) {
                            AndroidSupportInjection.inject(f)
                        }
                    }
                }, true
            )
        }
    }
}
