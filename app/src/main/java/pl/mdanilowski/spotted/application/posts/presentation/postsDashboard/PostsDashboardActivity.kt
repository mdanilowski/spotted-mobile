package pl.mdanilowski.spotted.application.posts.presentation.postsDashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import pl.mdanilowski.spotted.R
import pl.mdanilowski.spotted.base.BaseActivity
import pl.mdanilowski.spotted.databinding.ActivityPostsDashboardBinding
import timber.log.Timber

class PostsDashboardActivity :
    BaseActivity<ActivityPostsDashboardBinding, PostsDashboardViewModel>(
        PostsDashboardViewModel::class) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup(R.layout.activity_posts_dashboard, PostsDashboardViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.setSelectedCityAndFetchPosts(intent.getLongExtra(CITY_ID, CITY_ID_DEFAULT_VALUE))
    }

    override fun onStart() {
        super.onStart()
        viewModel.posts.observe(this, Observer { posts -> Timber.d("POSTS $posts") })
        viewModel.fetchNewPostsForCity()
    }

    companion object {
        const val CITY_ID = "city_id"
        const val CITY_ID_DEFAULT_VALUE : Long = 0
    }
}

fun Activity.startPostsDashboardActivity(cityId: Long) =
    Intent(this, PostsDashboardActivity::class.java)
        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        .putExtra(PostsDashboardActivity.CITY_ID, cityId)
        .let(this::startActivity)