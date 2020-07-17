package pl.mdanilowski.spotted.application.posts.presentation.addPost

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import pl.mdanilowski.spotted.R
import pl.mdanilowski.spotted.base.BaseActivity
import pl.mdanilowski.spotted.databinding.ActivityAddPostBinding

class AddPostActivity :
    BaseActivity<ActivityAddPostBinding, AddPostViewModel>(
        AddPostViewModel::class) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup(R.layout.activity_add_post, AddPostViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.findViewById(R.id.toolbar))
        supportActionBar?.title = "New spotted message"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}

fun Activity.startAddMessageActivity(context: Context) {
    startActivity(Intent(context, AddPostActivity::class.java))
}