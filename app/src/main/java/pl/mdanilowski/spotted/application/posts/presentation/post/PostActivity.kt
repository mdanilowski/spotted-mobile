package pl.mdanilowski.spotted.application.posts.presentation.post

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import pl.mdanilowski.spotted.R
import pl.mdanilowski.spotted.base.BaseActivity
import pl.mdanilowski.spotted.application.posts.domain.model.Post
import pl.mdanilowski.spotted.databinding.ActivityPostBinding

class PostActivity :
    BaseActivity<ActivityPostBinding, PostViewModel>(
        PostViewModel::class) {

    companion object {
        const val MESSAGE_TAG = "message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup(R.layout.activity_post, PostViewModel::class.java)
        binding.viewModel = viewModel
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.findViewById(R.id.toolbar))
        supportActionBar?.title = "Spotted message"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}

fun Activity.startMessageDetailsActivity(context: Context, msg: Post) {
    startActivity(
        Intent(context, PostActivity::class.java).putExtra(
            PostActivity.MESSAGE_TAG,
            msg
        )
    )
}