package pl.mdanilowski.spotted.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import pl.mdanilowski.spotted.R
import pl.mdanilowski.spotted.ui.enterCity.startEnterCityActivity
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_view)
    }

    override fun onResume() {
        super.onResume()
        compositeDisposable.add(
            Completable.complete()
                .delay(2, TimeUnit.SECONDS)
                .subscribe({ startEnterCityActivity(this) }, { t: Throwable -> Timber.e(t) })
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}