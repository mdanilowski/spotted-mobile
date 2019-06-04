package pl.mdanilowski.myapplication.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import pl.mdanilowski.myapplication.R
import pl.mdanilowski.myapplication.databinding.ActivitySplashViewBinding
import pl.mdanilowski.myapplication.ui.enterCity.startEnterCityActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivitySplashViewBinding>(this, R.layout.activity_splash_view)
    }

    override fun onResume() {
        super.onResume()
        startEnterCityActivity(this)
    }
}